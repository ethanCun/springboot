package com.example.demos.demoforappfront;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.demos.demoforappfront.dao"})
public class DemoForAppFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoForAppFrontApplication.class, args);
    }

}
