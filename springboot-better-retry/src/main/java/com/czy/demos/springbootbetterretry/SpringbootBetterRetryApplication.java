package com.czy.demos.springbootbetterretry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 参考链接：
 * 1. AtomicLong的使用： https://blog.csdn.net/weixin_39967234/article/details/81507478
 * 2. Java动态代理的使用： https://blog.csdn.net/yaomingyang/article/details/80981004
 *      cglib代理使用： https://www.cnblogs.com/writeLessDoMore/p/6973853.html
 * 3. retry参考链接： https://houbb.github.io/2018/08/08/retry#v12-%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F
 * 4. java字节码作用： https://blog.csdn.net/a4171175/article/details/90735888
 * */
@SpringBootApplication
@EnableRetry //表明开始使用spring retry
public class SpringbootBetterRetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBetterRetryApplication.class, args);
    }
}
