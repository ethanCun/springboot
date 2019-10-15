package com.czy.demos.springbootdeadletterdemo.RabbitmqTTLWithPlugin;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DelayedMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg, Integer seconds){

        System.out.println("插件发送时间: " + new Date() + " 内容: " + msg + " 间隔: " + seconds);

        this.rabbitTemplate.convertAndSend(DelayedRabbitMQConfig.DELAYED_EXCHANGE_NAME, DelayedRabbitMQConfig.DELAYED_ROUTING_KEY_NAME,
                msg, a -> {

            //用插件时调用setDelay
            a.getMessageProperties().setDelay(1000*seconds);

            return a;
        });
    }
}
