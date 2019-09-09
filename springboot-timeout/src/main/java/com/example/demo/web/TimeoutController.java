package com.example.demo.web;

import com.example.demo.service.async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

//springboot设置超时时间参考链接：https://blog.csdn.net/qudapeng351/article/details/90669316

@RestController
public class TimeoutController {

    @Autowired
    private async async;

    //设置睡眠5秒 application.properties超时时间配置为4s
    @RequestMapping(value = "/timeout")
    public Callable<String> timeout(String content) throws InterruptedException{

        //用Future<T>接受异步任务的返回值
//        String res = this.async.asyncTask(content).get();

        //返回Callable测试超时时间 直接返回object不生效
        return new Callable<String>() {

            @Override
            public String call() throws Exception {

                Thread.sleep(5000);

                return content;
            }
        };
    }
}
