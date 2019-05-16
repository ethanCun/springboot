package com.example.demo.TestBeans;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 配置Spring Boot通过@ConditionalOnProperty来控制Configuration是否生效
 *
 * 通过其两个属性name以及havingValue来实现的，
 * 其中name用来从application.properties中读取某个属性值
 * ，如果该值为空，则返回false;如果值不为空，则将该值与havingValue指定的值进行比较，
 * 如果一样则返回true;否则返回false。如果返回值为false，则该configuration不生效；为true则生效。
 * */
//@ConditionalOnProperty(name = "configure.czy.name", havingValue = "czy2")
@ConditionalOnProperty(prefix = "configure.czy", value = "name", havingValue = "czy")
@Configuration
public class TestBean {

    public TestBean(){

        System.out.println("test bean =========");
    }
}
