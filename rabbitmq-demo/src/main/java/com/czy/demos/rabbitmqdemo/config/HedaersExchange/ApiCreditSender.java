package com.czy.demos.rabbitmqdemo.config.HedaersExchange;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApiCreditSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    //HeaderExchange发送的消息 需要绑定headerValues
    public void creditBank(Map<String, Object> headerValues, String msg){

        amqpTemplate.convertAndSend("creditBankExchange", "credit.bank", getMessage(headerValues, msg));
    }

    public void creditFinance(Map<String, Object> headerValues, String msg){

        amqpTemplate.convertAndSend("creditFinanceExchange", "credit.finance", getMessage(headerValues, msg));
    }

    private Message getMessage(Map<String, Object> head, Object msg){

        MessageProperties messageProperties = new MessageProperties();

        for (Map.Entry<String, Object> entry : head.entrySet()) {

            //设置header绑定到消息
            messageProperties.setHeader(entry.getKey(), entry.getValue());
        }

        MessageConverter messageConverter = new SimpleMessageConverter();

        return messageConverter.toMessage(msg, messageProperties);
    }
}
