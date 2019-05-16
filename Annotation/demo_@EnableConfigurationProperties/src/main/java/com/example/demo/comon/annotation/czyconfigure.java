package com.example.demo.comon.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface czyconfigure {

    //attribute和value都指定时， 两者必须一致
    @AliasFor(value="value", annotation = Configuration.class)
    String value() default "";
}
