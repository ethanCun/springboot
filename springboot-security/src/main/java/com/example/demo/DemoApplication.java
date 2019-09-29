package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 参考链接：https://blog.csdn.net/zhuyongru/article/details/82108543
 *
 * */

@SpringBootApplication
//@mapperscan:用来扫描指定包下的mapper
@MapperScan(basePackages = "com.example.demo.dao")
//@componentscan:用来扫描标注了@Controller  @Service  @Repository @component
//@ComponentScan(basePackages = {"com.example.demo.dao", "com.example.demo.config"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
