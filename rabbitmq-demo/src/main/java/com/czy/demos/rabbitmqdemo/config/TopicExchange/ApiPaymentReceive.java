package com.czy.demos.rabbitmqdemo.config.TopicExchange;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ApiPaymentReceive {

    @RabbitListener(queues = "api.payment")
    @RabbitListener
    public void order(String msg){

        System.out.println("TopicExchange payment receive msg = " + msg);
    }
}
