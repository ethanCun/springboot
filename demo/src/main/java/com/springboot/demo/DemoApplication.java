package com.springboot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringbootApplication相当于@Configuration,@EnableAutoConfiguration和 @ComponentScan并具有他们的默认属性值
@SpringBootApplication
//扫描dao层
@MapperScan(basePackages = "com.springboot.demo.dao")
//开启事务
//@EnableTransactionManagement

public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        System.out.println("外部tomcat启动");
        return builder.sources(this.getClass());
    }
}
