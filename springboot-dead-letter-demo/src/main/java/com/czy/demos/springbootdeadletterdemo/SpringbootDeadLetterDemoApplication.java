package com.czy.demos.springbootdeadletterdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 关于死信：
 * https://www.cnblogs.com/mfrank/p/11184929.html#autoid-0-3-0
 *
 * 1. 配置业务队列，绑定到业务交换机上
 * 2. 为业务队列配置死信交换机和路由key
 * 3. 为死信交换机配置死信队列
 *
 * 关于延时队列ttl, 消费者收不到消息报错Channel shutdown: channel error; protocol method: #method<channel.close> 参考:
 * https://blog.csdn.net/qq_15071263/article/details/99976534
 * */
@SpringBootApplication
public class SpringbootDeadLetterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDeadLetterDemoApplication.class, args);
    }

}
