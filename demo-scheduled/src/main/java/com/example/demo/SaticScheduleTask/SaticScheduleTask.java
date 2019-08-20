package com.example.demo.SaticScheduleTask;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
//@Configuration
//@EnableScheduling
public class SaticScheduleTask {

    /**
     * 静态：基于注解
     * 基于注解@Scheduled默认为单线程，开启多个任务时，任务的执行时机会受上一个任务执行时间的影响
     * */
    //指定时间间隔 fixedRate: 任务两次执行的间隔是以两次的开始点为参考
//    @Scheduled(fixedRate = 5000)
    //指定延迟
//    @Scheduled(fixedDelay = 5000) fixedDelay: 任务两次执行间隔是以上一次结束和下一次开始为参考
    //指定cron，在线Cron表达式生成器: http://cron.qqe2.com/
    //0/2:表示每隔2秒触发一次 0-2：表示0 1 2s秒的时候触发 *：通配符 0，1，2：表示指定秒是触发  秒分时日月周年 中间用空格分开
    @Scheduled(cron = "0/2 * * * * ?")
    private void configurationTasks(){

        System.out.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
