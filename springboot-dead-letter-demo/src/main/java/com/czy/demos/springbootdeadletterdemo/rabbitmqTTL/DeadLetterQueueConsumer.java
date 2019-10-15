package com.czy.demos.springbootdeadletterdemo.rabbitmqTTL;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class DeadLetterQueueConsumer {

    /**演示从死信队列中读取消息 通过对比发送时间和接受时间可以说明 延时队列设置成功
     *
     * 发送消息 时间:Mon Oct 14 19:03:06 PDT 2019 内容: 1112 delayType = DELAY_10S
     *  收到消息A, 时间:Mon Oct 14 19:03:16 PDT 2019 内容: 1112
     *
     * 发送消息 时间:Mon Oct 14 19:03:26 PDT 2019 内容: 1112 delayType = DELAY_20S
     *  收到消息B, 时间:Mon Oct 14 19:03:46 PDT 2019 内容: 1112
     */
    @RabbitListener(queues = TTLConfig.DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws Exception{

        log.info("收到消息A, 时间:" + new Date() + " 内容: " + new String(message.getBody()));

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = TTLConfig.DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws Exception{

        log.info("收到消息B, 时间:" + new Date() + " 内容: " + new String(message.getBody()));

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 注意：
     * 如果使用在消息属性上设置TTL的方式，消息可能并不会按时“死亡“，因为RabbitMQ只会检查第一个消息是否过期，
     * 如果过期则丢到死信队列，索引如果第一个消息的延时时长很长，而第二个消息的延时时长很短，
     * 则第二个消息并不会优先得到执行。
     *
     * 例子： 先发送一个间隔为20s的消息 然后马上发送一个间隔为2s的消息 第二个消息等第一个消息超时成为死信后，第二个消息
     * 才成为死信。
     * 给指定消息发送ttl时间: Mon Oct 14 20:29:40 PDT 2019 内容: bbbaaaccca 间隔: 20
     * 给指定消息发送ttl时间: Mon Oct 14 20:29:43 PDT 2019 内容: bbbaaaccca 间隔: 2
     * 2019-10-14 20:30:00.078  INFO 32818 --- [ntContainer#2-1] c.c.d.s.r.DeadLetterQueueConsumer        : 收到消息C, 时间:Mon Oct 14 20:30:00 PDT 2019 内容: bbbaaaccca
     * 2019-10-14 20:30:00.078  INFO 32818 --- [ntContainer#2-1] c.c.d.s.r.DeadLetterQueueConsumer        : 收到消息C, 时间:Mon Oct 14 20:30:00 PDT 2019 内容: bbbaaaccca
     * */
    @RabbitListener(queues = TTLConfig.DEAD_LETTER_QUEUEC_NAME)
    public void receiveC(Message message, Channel channel) throws Exception{

        log.info("收到消息C, 时间:" + new Date() + " 内容: " + new String(message.getBody()));

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
