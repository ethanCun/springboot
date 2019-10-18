package com.czy.demos.springbootbetterretry.config.guavaRetry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.springframework.remoting.RemoteAccessException;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * Guava retryer在支持重试次数和重试频度控制基础上，能够兼容支持多个异常或者自定义实体对象的重试源定义，
 * 让重试功能有更多的灵活性。
 * Guava Retryer也是线程安全的，入口调用逻辑采用的是Java.util.concurrent.Callable的call方法。
 *
 * //步骤：
 *
 * 1. maven引入guava-retrying
 * 2. 自定义Callable接口， 供Guava调用
 * 3. 自定义Retryer对象并设置重试策略
 *
 */
public class GuavaRetry {

    public static void main(String[] args) {

        Callable<Boolean> callable = new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {

                System.out.println("calling...");

                return true;
            }
        };

        // RetryerBuilder 构建重试实例 retryer,可以设置重试源且可以支持多个重试源，可以配置重试次数或重试超时时间，以及可以配置等待时间间隔
        Retryer<Boolean> retryer = RetryerBuilder.
                        <Boolean>newBuilder()
                //抛出runtime异常、checked异常时都会重试，但是抛出error不会重试。
                        .retryIfException()
                //返回false也需要重试
                        .retryIfResult(Predicates.equalTo(false))
                //重调策略
                        .withWaitStrategy(WaitStrategies.fixedWait(5, TimeUnit.SECONDS))
                //尝试次数
                        .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                        .build();

        try {

            retryer.call(callable);

        }catch (RetryException e){

            System.out.println("RetryException");
            e.printStackTrace();
        }catch (ExecutionException e){

            System.out.println("ExecutionException");
            e.printStackTrace();
        }

    }
}
