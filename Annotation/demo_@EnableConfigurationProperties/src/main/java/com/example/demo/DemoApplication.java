package com.example.demo;

import com.example.demo.Config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties用于使使用 @ConfigurationProperties 注解的类生效。
//在使用 @ConfigurationProperties 注解的类上加上@component可以达到同样的效果
//@EnableConfigurationProperties({Config.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
