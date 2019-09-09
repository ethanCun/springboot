package com.example.demo;

import com.example.demo.service.async;
import com.example.demo.service.sync;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private sync sync;
    @Autowired
    private async async;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testSync() throws Exception{

        sync.syncTask1();
        sync.syncTask2();
        sync.syncTask3();
    }

    @Test
    public void testAsync() throws Exception{

        long start = System.currentTimeMillis();

        Future<String> result1 = async.asyncTask1();
        Future<String> result2 = async.asyncTask2();
        Future<String> result3 = async.asyncTask3();

        while (true){

            if (result1.isDone() && result2.isDone() && result3.isDone()){

                break;
            }

            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("全部任务完成: " + (end-start));
    }
}
