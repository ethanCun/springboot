package com.example.demojpush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DemoJpushApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJpushApplication.class, args);
    }

}
