package com.example.demo.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@ComponentScan(basePackages = "com.example.demo.Service")
@EnableAsync //启用异步任务
public class ThreadConfig extends AsyncConfigurerSupport {

    //执行需要依赖线程池，重写AsyncConfigurerSupport配置线程池
    @Override
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(10);

        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor;
    }

    // 执行需要依赖线程池，这里就来配置一个线程池
//    @Bean
//    public Executor executor(){
//
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setCorePoolSize(5);
//        threadPoolTaskExecutor.setMaxPoolSize(10);
//        threadPoolTaskExecutor.setQueueCapacity(12);
//
//        threadPoolTaskExecutor.initialize();
//
//        return threadPoolTaskExecutor;
//    }

}
