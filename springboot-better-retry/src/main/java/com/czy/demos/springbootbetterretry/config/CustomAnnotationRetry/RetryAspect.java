package com.czy.demos.springbootbetterretry.config.CustomAnnotationRetry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class RetryAspect {

    //定义切点 可见性 返回值 包名 类 方法
    @Pointcut("execution(public * com.czy.demos..*.*(..)) &&" +
                "@annotation(com.czy.demos.springbootbetterretry.config.CustomAnnotationRetry.CustomRetryAnnotation)")
    public void retryPoint(){};


    @Around("retryPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable{

        Method method;

        //获取方法
        try {

            Signature signature = point.getSignature();
            MethodSignature methodSignature = (MethodSignature)signature;
            Object target = point.getTarget();

            method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

            System.out.println("target = " + target);
            System.out.println("signture = " + methodSignature.getName() + " - " + methodSignature.getDeclaringType().getName());
            System.out.println("method = " + method.getName());

        }catch (NoSuchMethodException e){

            throw new RuntimeException(e);
        }

        //获取重试标注的内容
        CustomRetryAnnotation retryAnnotation = (CustomRetryAnnotation)method.getAnnotation(CustomRetryAnnotation.class);

        //获取最大重试次数
        int maxTryCount = retryAnnotation.retryTimes();

        if (maxTryCount <= 1){

            //重试次数小于1直接继续执行
            return point.proceed();
        }

        //重试次数
        int times = 0;

        while (times < maxTryCount){

            try {

                //重试次数大于1
                return point.proceed();

            }catch (Throwable e){

                times++;

                //超过注解内自定义的最大重试次数
                if (times >= maxTryCount){

                    throw new Throwable(e);
                }

            }
        }

        return null;
    }

}
