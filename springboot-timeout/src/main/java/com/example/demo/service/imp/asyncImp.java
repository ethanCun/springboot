package com.example.demo.service.imp;

import com.example.demo.service.async;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service("async")
public class asyncImp implements async {

    //注意异步一定要用Future<T>来接受返回的值，不然获到的值为null
    @Async
    @Override
    public Future<String> asyncTask(String content) throws Exception{

        Thread.sleep(5000);

        System.out.println("content = " + content);

        return new AsyncResult<>(content);
    }
}

