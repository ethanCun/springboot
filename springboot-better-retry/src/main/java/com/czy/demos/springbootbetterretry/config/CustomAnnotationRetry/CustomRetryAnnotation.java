package com.czy.demos.springbootbetterretry.config.CustomAnnotationRetry;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomRetryAnnotation {

    //重试次数
    int retryTimes() default 5;
}
