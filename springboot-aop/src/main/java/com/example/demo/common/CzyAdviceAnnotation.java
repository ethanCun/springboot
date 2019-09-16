package com.example.demo.common;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface CzyAdviceAnnotation {

    String name() default "";
}
