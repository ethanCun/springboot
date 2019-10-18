package com.czy.demos.springbootbetterretry.config.SpringRetry;


import com.czy.demos.springbootbetterretry.Exception.CzyException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * spring retry缺陷：
 * Spring Retry 提倡以注解的方式对方法进行重试，重试逻辑是同步执行的，重试的“失败”针对的是Throwable，
 * 如果你要以返回值的某个状态来判定是否需要重试，可能只能通过自己判断返回值然后显式抛出异常了。
 */
@Service("springRetryService")
public class SpringRetryService {

    /**
     * 在入口类加上 @EnableRetry 表明开始使用spring retry
     * 重试条件：遇到 RuntimeException
     * 重试次数：10
     * 重试策略：重试的时候等待 1S, 后面时间依次变为原来的 2 倍数。
     * 熔断机制：全部重试失败，则调用 recover() 方法。
     */
    @Retryable(value = RuntimeException.class,
                maxAttempts = 10,
                backoff = @Backoff(delay = 1000L, multiplier = 2))
    public void call(){

        /**
         调用时间: Thu Oct 17 01:18:08 PDT 2019
         调用时间: Thu Oct 17 01:18:09 PDT 2019
         调用时间: Thu Oct 17 01:18:11 PDT 2019
         调用时间: Thu Oct 17 01:18:15 PDT 2019
         调用时间: Thu Oct 17 01:18:23 PDT 2019
         调用时间: Thu Oct 17 01:18:39 PDT 2019
         调用时间: Thu Oct 17 01:19:09 PDT 2019
         调用时间: Thu Oct 17 01:19:39 PDT 2019
         调用时间: Thu Oct 17 01:20:09 PDT 2019
         调用时间: Thu Oct 17 01:20:39 PDT 2019
         recover e = rpc调用失败
         */

        System.out.println("调用时间: " + new Date());

        throw new RuntimeException("rpc调用失败");
    }


    //recover机制 对调方法
    @Recover
    public void recover(RuntimeException e){

        System.out.println("recover e = " + e.getMessage());
    }
}
