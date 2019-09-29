package com.example.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.example.demo.config.TargetDataSource)")
    public void annotationPoint(){

    }

    @Pointcut("execution(public * com.example.demo.*..*(..))")
    public void excludeService(){

    }

    @Around(value = "annotationPoint()&&excludeService()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) point.getSignature();

        Method targetMethod = methodSignature.getMethod();

        //拦截@TargetDataSource注解的dataSource
        if (targetMethod.isAnnotationPresent(TargetDataSource.class)){

            //根据注解获取数据库
            String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).dataSource();
            //设置数据库
            DynamicDataSourceHolder.setDataSource(targetDataSource);
        }

        //继续执行
        Object result = point.proceed();

        //清除数据库
        DynamicDataSourceHolder.cleanDataSource();

        return result;
    }
}
