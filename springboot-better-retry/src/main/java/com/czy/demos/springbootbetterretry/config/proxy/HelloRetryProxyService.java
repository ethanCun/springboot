package com.czy.demos.springbootbetterretry.config.proxy;

import com.czy.demos.springbootbetterretry.NormalRetry.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloRetryProxyService implements HelloService {

    @Autowired
    private HelloService helloService;

    @Override
    public String saySth(String something) {

        int maxTimes = 10;

        String s = "";

        for (int retry = 1; retry < maxTimes; retry++){

            try {

                s = helloService.saySth(something);

                log.info("代理模式输出: " + something);

                return s;

            }catch (Exception e){

                log.info("代理模式重试失败~");
            }

            //延时一秒
            try {

                Thread.sleep(1000);

            }catch (InterruptedException e){

                e.printStackTrace();
            }
        }

        throw new RuntimeException("重试次数已用完");
    }
}
