package com.example.demojpush.config;

import cn.jpush.api.JPushClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
@ConfigurationProperties
public class JPushConfig {

    @Value("${push.appkey}")
    private String appkey;

    @Value("${push.secret}")
    private String secret;

    private JPushClient jPushClient;

    @PostConstruct
    public void initJPUshClient(){

        System.out.println("appkey = " + appkey + " secret = " + secret);

        jPushClient = new JPushClient(secret, appkey);
    }

    public JPushClient getjPushClient() {
        return jPushClient;
    }
}
