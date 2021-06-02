#!/bin/sh
###################################
#注意：需要根据实际情况修改的参数有：
#1.RUNNING_USER：执行程序的用户名
#2.JAVA_HOME：Java安装目录
#3.APP_HOME：应用classes的上一级目录
#4.APP_NAME：应用名称
#5.APP_ENV：应用环境
#6.SERVICE_NETWORK：指定网卡，用于后续获取网卡的IP地址
#7.getServiceHealthIP：获取网卡的IP地址，对于不同机器可能获取方式不同
###################################

#JDK所在路径
JAVA_HOME=/usr/local/java/jdk1.8.0_251

#执行程序启动所使用的系统用户，考虑到安全，推荐不使用root帐号
RUNNING_USER=xinhua

#应用所在的目录
APP_HOME=/home/xinhua/application/act
#应用jar包名称(不含.jar)
APP_NAME=act
APP_JAR=$APP_NAME.jar
APP_PID_FILE=$APP_NAME.pid
APP_ENV=test
#微服务注册网卡
SERVICE_NETWORK=eth0

#环境变量
source ./env.sh


#应用运行参数
PROG_ARGS="-Xms4G -Xmx4G -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:log/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=dumpfile.hprof"

#JVM参数
JAVA_OPTS="-server"

#初始化PID变量（全局）
PID=0
#初始化SERVICE_HEALTH_IP变量
SERVICE_HEALTH_IP=""

###################################
#(函数)获取指定网卡的IP地址，在多网卡环境下，指定通讯网卡
###################################
getServiceHealthIP() {
    ipaddress=`/sbin/ifconfig $SERVICE_NETWORK | grep "inet " | awk '{print $2}'`
    if [ $? -ne 0 ]; then
        echo 'Can not get ipaddress!'
    else
        SERVICE_HEALTH_IP="--spring.cloud.inetutils.preferred-networks=$ipaddress"
    fi
}

###################################
#(函数)检查执行用户
###################################
checkRunner() {
    USERNAME=`whoami`
    if [ $USERNAME != "$RUNNING_USER" ]; then
        echo "用户$USERNAME不允许执行本服务，请使用用户$RUNNING_USER，程序将关闭!"
        exit
    fi
}

###################################
#(函数)检查程序是否正在执行
#
#说明：
#1、如果程序正在运行，则将pid文件中保存的pid赋值给全局变量PID，否则全局变量PID为0
#2、可能有多个重命的程序正在运行，此时会依次判断这些程序的pid是否有pid文件中保存的pid，依次判断目标程序是否正在运行
###################################
checkPID() {
    if [ -f $APP_HOME/$APP_PID_FILE ]; then
        PID=`cat $APP_HOME/$APP_PID_FILE`
        if [ -z $PID ]; then
            PID=0
        else
            found=0
            for x in `ps -ef | grep -w "$APP_JAR" | grep -v "grep" | awk '{print $2}'`
            do
                if [ $x = $PID ]; then
                    found=1
                    break;
                fi
            done
            if [ $found -eq 0 ]; then
                PID=0
            fi
        fi
    fi
}

###################################
#(函数)循环检查程序是否正在执行（返回值1表示正在执行），默认循环10次，每次间隔3s
#
#说明：
#1.第一个参数是开关选项，取值为start|stop；第二个参数是循环次数；第三个参数是间隔时间
#2.如果开关选项是start，则checkPID之后，如果PID不为0，说明程序已经成功运行，则函数结束
#3.如果开关选项是stop，则checkPID之后，如果PID为0，说明程序已经成功停止，则函数结束
#4.如果开关选项是其他情况，则checkPID之后，函数直接结束
###################################
loopCheckPID() {
    option=$1
    retry=${2:-10}
    interval=${3:-3}

    for ((i=1; i<=retry; i++))
    do
        echo $i"/"$retry" attempt in progress..."
        sleep $interval
        checkPID
        if [ $option = "start" ]; then
            if [ $PID -ne 0 ]; then
                return 1
            fi
        elif [ $option = "stop" ]; then
            if [ $PID -eq 0 ]; then
                return 1
            fi
        else
            return 1
        fi
    done
    return 0
}

###################################
#(函数)启动程序
#
#说明：
#1.启动程序并将pid保存到pid文件中
#2.验证程序是否启动成功，如果成功，则打印[OK]，否则打印[Failed]
###################################
start() {
    checkRunner
    checkPID
#    getServiceHealthIP

    if [ $PID -ne 0 ]; then
        echo "================================"
        echo "Warning: $APP_NAME already started!(pid=$PID)"
        echo "================================"
    else
        echo "Starting $APP_NAME ..."
     #   nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar $APP_HOME/$APP_JAR $SERVICE_HEALTH_IP --spring.profiles.active=$APP_ENV $PROG_ARGS >/dev/null 2>&1 &
     #   nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar $APP_HOME/$APP_JAR --spring.profiles.active=$APP_ENV $PROG_ARGS >/dev/null 2>&1 &
        nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar $APP_HOME/$APP_JAR --spring.profiles.active=$APP_ENV $PROG_ARGS 2>&1 &
        echo $! > $APP_HOME/$APP_PID_FILE
        loopCheckPID start 20 2
        if [ $? -eq 1 ]; then
            echo "(pid=$PID)[OK]"
        else
            echo "[Failed]"
        fi
    fi
}

###################################
#(函数)停止程序
#
#说明：
#1. 如果程序已经启动，则执行下一步；否则，提示程序未运行
#2. 使用kill pid命令进行优雅杀死进程（等待时间为120s）
#3. 如果进程杀死成功，则打印[OK]，否则打印[Failed]并开始强杀，最后删除pid文件
###################################
stop() {
    checkRunner
    checkPID

    if [ $PID -ne 0 ]; then
        echo "Stopping $APP_NAME ...(pid=$PID)"
        kill $PID
        loopCheckPID stop 20 2
        if [ $? -eq 1 ]; then
            echo "[OK]"
        else
            echo "[Failed]"
            echo "Forcing kill $APP_NAME ...(pid=$PID)"
            kill -9 $PID
            echo "[OK]"
        fi
        rm -rf $APP_HOME/$APP_PID_FILE
    else
        echo "================================"
        echo "Warning: $APP_NAME is not running"
        echo "================================"
    fi
}

###################################
#(函数)重启程序
###################################
restart() {
    checkRunner

    echo "Restarting $APP_NAME ..."
    sh $0 stop
    sleep 1
    sh $0 start
}

###################################
#(函数)检查程序运行状态
###################################
status() {
    checkRunner
    checkPID

    if [ $PID -ne 0 ];  then
        echo "$APP_NAME is running(pid=$PID)"
    else
        echo "$APP_NAME is not running"
    fi
}

###################################
#(函数)打印系统环境参数
###################################
info() {
    checkRunner
    echo "System Information:"
    echo "****************************"
    echo `cat /etc/redhat-release`
    echo `uname -m`
    echo
    echo "JAVA_HOME=$JAVA_HOME"
    echo `$JAVA_HOME/bin/java -version`
    echo "APP_HOME=$APP_HOME"
    echo "APP_JAR=$APP_JAR"
    echo "****************************"
}

###################################
#读取脚本的第一个参数($1)，进行判断
#参数取值范围：{start|stop|restart|status|info}
#如参数不在指定范围之内，则打印帮助信息
###################################
case $1 in
    "start")
        start
        ;;
    "stop")
        stop
        ;;
    "restart")
        restart
        ;;
    "status")
        status
        ;;
    "info")
        info
        ;;
    *)
        echo "Usage: $0 {start|stop|restart|status|info}"
        ;;
esac
exit 0
