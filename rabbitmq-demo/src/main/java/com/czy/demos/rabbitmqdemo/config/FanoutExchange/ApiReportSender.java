package com.czy.demos.rabbitmqdemo.config.FanoutExchange;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiReportSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void report(String msg){

        amqpTemplate.convertAndSend("reportExchange", "api.generate.reports", msg);
    }
}
