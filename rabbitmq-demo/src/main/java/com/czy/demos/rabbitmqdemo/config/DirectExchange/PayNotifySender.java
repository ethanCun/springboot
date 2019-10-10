package com.czy.demos.rabbitmqdemo.config.DirectExchange;

import com.czy.demos.rabbitmqdemo.pojo.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 *
 * 添加一个消息发送类（生产者）
 *
 * 将消息发送至默认的交换机且routingKey为notify.payment
 */
@Component
public class PayNotifySender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sender(String msg){

        rabbitTemplate.convertAndSend("notify.payment", msg);
    }

    public void sendObj(User user){

        rabbitTemplate.convertAndSend("notify.payment", user);
    }

    public void sendObjAndReceive(User user){

        User user1 = (User) rabbitTemplate.convertSendAndReceive("notify.payment", user);

        System.out.println("RPC 返回的user: " + user1);
    }
}
