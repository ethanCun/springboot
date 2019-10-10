package com.czy.demos.rabbitmqdemo.config.DirectExchange;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DirectExchange是RabbitMQ的默认交换机，直接使用routingKey匹配队列。
 * 添加一个配置类 配置一个routingKey为notify.payment的消息队列
*/
@Configuration
public class DirectExchangeConfig {

    @Bean
    public Queue paymentNotifyQueue(){

        return new Queue("notify.payment");
    }
}
