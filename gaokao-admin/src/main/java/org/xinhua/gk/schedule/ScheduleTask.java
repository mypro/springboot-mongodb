package org.xinhua.gk.schedule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.xinhua.gk.service.BaseDictService;

@Configuration
@EnableScheduling
@ConditionalOnProperty(prefix = "schedule", name = "actScheduleSwitch", havingValue = "enable")
public class ScheduleTask {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);



    @Autowired
    private BaseDictService baseDictService;


    /**
     * 查看定时任务状态
     */
    @SuppressWarnings("unchecked")
    @Scheduled(cron = "0 0/10 * * * ?")
    private void tasks() {
        logger.info("* * ** * ** * ** * ** * * task working * * ** * ** * ** * ** * ** * *");
    }

}
