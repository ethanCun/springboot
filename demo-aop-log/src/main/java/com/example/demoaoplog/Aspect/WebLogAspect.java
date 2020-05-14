package com.example.demoaoplog.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

//日志切面 打印请求地址 方式  参数等
@Component
@Aspect
@Slf4j
public class WebLogAspect {

    //定义一个切点: controller1
    @Pointcut("execution(public * com.example.demoaoplog.controller..*.*(..))")
    public void logPoint(){};

    //定义一个切点：controller2
    @Pointcut("execution(public * com.example.demoaoplog.controller2..*.*(..))")
    public void logPoint2(){};

    @Before("logPoint() || logPoint2()")
    public void logBefore(JoinPoint joinPoint){

        //获取请求
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        //记录请求的详细信息
        log.info("请求地址: " + request.getRequestURL().toString());
        log.info("请求方式: " + request.getMethod());
        log.info("请求sessionId: " + request.getRequestedSessionId());
        log.info("请求query参数: " + request.getQueryString());
        log.info("请求map参数: " + request.getParameterMap().toString());
        log.info("请求发起地址: " + request.getRemoteAddr());
        log.info("包名：" + joinPoint.getSignature().getDeclaringTypeName() + " 方法名:" + joinPoint.getSignature().getName());

        //获取body里面的数据
        try {

            //这里的getReader方法在ResquestWrapper中已经被重写
            BufferedReader reader = request.getReader();

            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null){

                sb.append(line);
            }

            log.info("请求body数据: " + sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(" 《《《 上条日志内容结束 》》》");
        log.info("=================================================================");
        log.info("《《《 下条日志内容开始 》》》\n");
    }
}
