package com.czy.demos.rabbitmqdemo.config.HedaersExchange;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ApiCreditReceive {

    @RabbitListener(queues = "credit.bank")
    @RabbitHandler
    public void creditBank(String msg){

        System.out.println("credit bank receive msg = " + msg);
    }

    @RabbitListener(queues = "credit.finance")
    @RabbitHandler
    public void creditFinance(String msg){

        System.out.println("credit finance receive msg = " + msg);
    }
}
