package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(value = "com.example.demo.dao")
@EnableSwagger2
public class DemoApplication {

    //mybatis-generator: 终端生成指令：mvn mybatis-generator:generate

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
