package com.czy.demos.rabbitmqdemo.config.TopicExchange;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiCoreSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void user(String msg){

        //第一个参数 交换机 队列routingkey 消息Message
        amqpTemplate.convertAndSend("coreExchange", "api.core.user", msg);
    }

    public void userQuery(String msg){

        amqpTemplate.convertAndSend("coreExchange", "api.core.user.query", msg);
    }
}
