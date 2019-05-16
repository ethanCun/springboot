package com.example.demo.Service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

@Service(value = "asyncTaskService")
public class AsyncTaskService {


    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
    @Async
    public void test1(){

        System.out.println("test1 = " + Thread.currentThread().getName() + " " + UUID.randomUUID().toString());

        try {
            Thread.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    @Async
    public void test2(){

        System.out.println("test2 = " + Thread.currentThread().getName() + " " + UUID.randomUUID().toString());

        try {

            //new Random().nextInt(10)
            Thread.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
