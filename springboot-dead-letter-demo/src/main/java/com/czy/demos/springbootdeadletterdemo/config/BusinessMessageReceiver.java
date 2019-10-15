package com.czy.demos.springbootdeadletterdemo.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


//@Component
//@Slf4j
public class BusinessMessageReceiver {

//    /**
//     * 下列情况会出现死信：
//     *
//     * 1. 消息被否定确认，使用 channel.basicNack 或 channel.basicReject ，并且此时requeue 属性被设置为false。
//     * 2. 消息在队列的存活时间超过设置的TTL时间。
//     * 3. 消息队列的消息数量已经超过最大队列长度。
//     * */
//    @RabbitListener(queues = RabbitMQConfig.BUSINESS_QUEUEA_NAME)
//    public void receiveA(Message message, Channel channel) throws Exception{
//
//        String msg = new String(message.getBody());
//
//        log.info("收到业务消息A: " + msg);
//
//        boolean ack = true;
//        Exception exception = null;
//
//        try {
//
//            if (msg.contains("deadLetter")){
//
//                throw new RuntimeException("dead letter exception");
//            }
//
//        }catch (Exception e){
//
//            ack = false;
//            exception = e;
//        }
//
//        if (!ack){
//
//            log.error("消息消费发生异常，error msg:{}", exception.getMessage(), exception);
//
//            //使之出现死信
////            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//        }else {
//
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        }
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.BUSINESS_QUEUEB_NAME)
//    public void receiveB(Message message, Channel channel) throws Exception{
//
//        log.info("收到业务消息B: " + new String(message.getBody()));
//
//        //第一个参数: 消息tag
//        //第二个参数：是否拒绝所有消息
//        //第三个参数：是否重新排队
//        System.out.println("tag = " + message.getMessageProperties().getDeliveryTag());
//        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//    }
}
