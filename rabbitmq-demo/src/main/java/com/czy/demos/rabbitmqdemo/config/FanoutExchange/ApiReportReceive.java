package com.czy.demos.rabbitmqdemo.config.FanoutExchange;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ApiReportReceive {

    @RabbitHandler
    @RabbitListener(queues = "api.report.payment")
    public void paymemnt(String msg){

        System.out.println("api.report.payment msg = " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.report.refund")
    public void refund(String msg){

        System.out.println("api.report.refund msg = " + msg);
    }
}
