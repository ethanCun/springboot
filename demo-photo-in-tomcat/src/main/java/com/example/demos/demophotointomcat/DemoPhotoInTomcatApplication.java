package com.example.demos.demophotointomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class DemoPhotoInTomcatApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoPhotoInTomcatApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoPhotoInTomcatApplication.class);
    }
}
