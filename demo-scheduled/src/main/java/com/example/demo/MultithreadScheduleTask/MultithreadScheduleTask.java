package com.example.demo.MultithreadScheduleTask;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@Component
@Configuration
@EnableScheduling
@EnableAsync
public class MultithreadScheduleTask {

    @Async
    @Scheduled(fixedDelay = 2000)
    public void first() throws InterruptedException{

        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
//        Thread.sleep(1000*2);
    }

    @Async
    @Scheduled(fixedDelay = 2000)
    public void second() throws InterruptedException{

        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
//        Thread.sleep(1000*2);
    }
}
