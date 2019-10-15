package com.czy.demos.springbootdeadletterdemo.rabbitmqTTL;

import com.czy.demos.springbootdeadletterdemo.pojo.DelayTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class DelayMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //给指定队列设置ttl
    //发送消息到延时交换机 根据时间分配到不同的延时队列
    //如果此时rabbitmq后端队列的ttl和配置中的不一样 最好删除原来的队列 重启测试
    public void sendWithQueueTTL(String msg, DelayTypeEnum delayTypeEnum){

        System.out.println("发送消息 时间:" + new Date() + " 内容: " + msg + " delayType = " + delayTypeEnum);

        switch (delayTypeEnum){

            case DELAY_10S:

                //队列ttl为10s
                this.rabbitTemplate.convertAndSend(TTLConfig.DELAY_EXCHANGE_NAME, TTLConfig.DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case DELAY_20S:

                //队列ttl为20s
                this.rabbitTemplate.convertAndSend(TTLConfig.DELAY_EXCHANGE_NAME, TTLConfig.DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
        }
    }


    //给指定消息设置ttl
    public void sendWithMessageTTL(String msg, Integer ttl){

        System.out.println("给指定消息发送ttl时间: " + new Date() + " 内容: " + msg + " 间隔: " + ttl);

        this.rabbitTemplate.convertAndSend(TTLConfig.DELAY_EXCHANGE_NAME, TTLConfig.DELAY_QUEUEC_ROUTING_KEY,
                msg, a -> {

                a.getMessageProperties().setExpiration(String.valueOf(ttl*1000));

                return a;
        });
    }
}
