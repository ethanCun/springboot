package com.czy.demos.rabbitmqdemo.config.TopicExchange;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ApiCoreReceive {

    @RabbitListener(queues = "api.core")
    @RabbitHandler
    public void user(String msg){

        System.out.println("TopicExchange core receive msg = " + msg);
    }
}
