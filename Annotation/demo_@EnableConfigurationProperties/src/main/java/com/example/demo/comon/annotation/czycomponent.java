package com.example.demo.comon.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

//为component取别名
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface czycomponent {

    //attribute和value必须一致
    @AliasFor(value="value",  annotation = Component.class)
    String value() default "";
}
