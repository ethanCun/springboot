package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication

//注意：
//使用通用mapper:
//Spring-boot启动类，@MapperScan仅扫描业务接口包，不能扫描本地通用Mapper接口包，
//否则报java.lang.ClassCastException: sun.reflect.generics.reflectiveObjects.TypeVariableImpl
//cannot be cast to java.lang.Class异常

//所以此处不能将IMapper放到dao层下面
@MapperScan(basePackages = "com.example.demo.dao")

public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}
