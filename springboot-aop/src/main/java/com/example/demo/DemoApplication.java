package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//因为在AOP的默认配置属性中，spring.aop.auto属性默认是开启的，
//也就是说只要引入了AOP依赖后，默认已经增加了@EnableAspectJAutoProxy。
//@EnableAspectJAutoProxy
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
