package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot-security集成：https://www.jianshu.com/p/246e4fec1469
 * springboot-security注解使用：https://www.jianshu.com/p/c159afb7bd4a
 * Httpsecurity使用：https://blog.csdn.net/dawangxiong123/article/details/68960041
 * springboot-security用户切换：https://my.oschina.net/go4it/blog/1591720
 * */

@SpringBootApplication
//@mapperscan:用来扫描指定包下的mapper
@MapperScan(basePackages = "com.example.demo.dao")
//@componentscan:用来扫描标注了@Controller  @Service  @Repository  @component
//@ComponentScan(basePackages = {"com.example.demo.dao", "com.example.demo.config"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
