package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 开启热部署需要在idea中设置自动编译
 * idea设置自动编译参考链接：
 * https://jingyan.baidu.com/article/ca2d939d5b0c05eb6d31ce77.html
 * */

/**
 *
 * 修改类–>保存：应用会重启
 *     修改配置文件–>保存：应用会重启
 *     修改页面–>保存：应用不会重启，但会重新加载，
 *     页面会刷新（原理是将spring.thymeleaf.cache设为false，参考:Spring Boot配置模板引擎）
 * */

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}
