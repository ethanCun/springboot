package com.example.demo.service.imp;

import com.example.demo.service.sync;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("sync")
public class syncImp implements sync {

    public static Random random = new Random();

    @Override
    public void syncTask1() throws Exception{

        System.out.println("task1开始:");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();

        System.out.println("task1结束，耗时：" + (end-start));
    }

    @Override
    public void syncTask2() throws Exception{

        System.out.println("task2开始:");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();

        System.out.println("task2结束，耗时：" + (end-start));
    }

    @Override
    public void syncTask3() throws Exception{

        System.out.println("task3开始:");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();

        System.out.println("task3结束，耗时：" + (end-start));
    }
}
