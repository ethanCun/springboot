package com.example.demojackson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.example.demojackson.dao"})
@SpringBootApplication
public class DemoJacksonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJacksonApplication.class, args);
    }

}
