package com.czy.demos.rabbitmqdemo.config.DirectExchange;

import com.czy.demos.rabbitmqdemo.pojo.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 添加一个消息监听类（消费者）
 *
 * 监听routingKey为notify.payment的队列消息
 * */
@Component
@RabbitListener(queues = "notify.payment")
public class PayNotifyReceive {

    /**
     * 虽然RabbitMQ支持RPC接口调用，但不推荐使用。
     *
     * 原因：
     * 1）RPC默认为单线程阻塞模型，效率极低。
     * 2）需要手动实现多线程消费。
     * */
    @RabbitHandler
    public User receive1(User user){

        System.out.println("收到消息msg = " + user);
        return user;
    }

//    @RabbitHandler
//    public void receive2(String msg){
//
//        System.out.println("2收到消息msg = " + msg);
//    }
//
//    @RabbitHandler
//    public void receive3(String msg){
//
//        System.out.println("3收到消息msg = " + msg);
//    }
}
