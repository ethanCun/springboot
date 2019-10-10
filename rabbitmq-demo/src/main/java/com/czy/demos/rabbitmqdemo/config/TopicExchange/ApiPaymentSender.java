package com.czy.demos.rabbitmqdemo.config.TopicExchange;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiPaymentSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void order(String msg){

        amqpTemplate.convertAndSend("paymentExchange", "api.payment.order", msg);
    }

    public void orderQuery(String msg){

        amqpTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
    }

    public void orderDetailQuery(String msg){

        amqpTemplate.convertAndSend("paymentExchange", "api.payment.order.detail.query", msg);
    }
}
