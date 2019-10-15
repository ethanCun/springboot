package com.czy.demos.springbootdeadletterdemo.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@Slf4j
public class DeadLetterMessageReceiver {

//    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUEA_NAME)
//    public void receiveA(Message message, Channel channel) throws Exception{
//
//        log.info("收到死信消息A: " + new String(message.getBody()));
//        log.info("死信消息A内容: " + message.getMessageProperties());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUEB_NAME)
//    public void receiveB(Message message, Channel channel) throws Exception{
//
//        log.info("收到死信消息B: " + new String(message.getBody()));
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
}
