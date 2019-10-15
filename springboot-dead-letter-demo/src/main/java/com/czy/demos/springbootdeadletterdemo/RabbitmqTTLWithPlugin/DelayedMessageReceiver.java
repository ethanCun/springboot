package com.czy.demos.springbootdeadletterdemo.RabbitmqTTLWithPlugin;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DelayedMessageReceiver {

    /**
     * 使用rabbitmq_delayed_message_exchange插件测试结果:
     *
     * 插件发送时间: Mon Oct 14 22:35:21 PDT 2019 内容: 111 间隔: 20
     * 插件发送时间: Mon Oct 14 22:35:24 PDT 2019 内容: 222 间隔: 2
     * rabbitmq 插件接受时间: Mon Oct 14 22:35:26 PDT 2019 内容: 222
     * rabbitmq 插件接受时间: Mon Oct 14 22:35:41 PDT 2019 内容: 111
     * */
    @RabbitListener(queues = DelayedRabbitMQConfig.DELAYED_QUEUE_NAME)
    public void delayedReceive(Message message, Channel channel) throws Exception{

        System.out.println("rabbitmq 插件接受时间: " + new Date() + " 内容: " + new String(message.getBody()));

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
