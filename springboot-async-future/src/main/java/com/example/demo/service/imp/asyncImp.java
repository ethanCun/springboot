package com.example.demo.service.imp;

import com.example.demo.service.async;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

@Service("async")
public class asyncImp implements async {

    private static Random random = new Random();

    @Async
    @Override
    public Future<String> asyncTask1() throws Exception{

        System.out.println("async1开始:");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("result1 = " + (end-start));

        return new AsyncResult<>("async1结束,耗时:" + (end-start));
    }

    @Async
    @Override
    public Future<String> asyncTask2() throws Exception{

        System.out.println("async2开始:");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("result2 = " + (end-start));

        return new AsyncResult<>("async2结束,耗时:" + (end-start));
    }

    @Async
    @Override
    public Future<String> asyncTask3() throws Exception{

        System.out.println("async3开始:");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("result3 = " + (end-start));

        return new AsyncResult<>("async3结束,耗时:" + (end-start));
    }
}
