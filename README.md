#切换系统

http://maximus.xinhua-news.cn:8080/emily/sysconfig/selectSys?systemId=88


#请求数据权限
http://maximus.xinhua-news.cn:8080/emily/api/data?systemId=88&userId=SN_2018052317281143369



测试环境
172.22.27.160  xinhua 用户部署

程序目录 /home/xinhua/application/act
修改环境变量 , 编辑 env.sh
重启 appctl.sh restart
停止 appctl.sh stop
启动 appctl.sh start

日志目录 /home/xinhua/application/act/logs
后台管理前端文件目录 /home/xinhua/application/actAdmin
用户端前端文件目录 /home/xinhua/application/actClient



curl  'http://10.244.152.232/act/video/callback' -X 'POST' -d '{"id":"123asd"}' -H "Content-Type: application/json"

curl 'http://xinhua.e.lanxin.cn/sns/userinfo?access_token=access_token&mobile=18610246546' 


登出地址
https://course.xinhua-news.cn/actAdmin/logout
