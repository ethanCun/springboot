package com.czy.demos.springbootdeadletterdemo.rabbitmqTTL;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq延时队列学习参考链接：https://www.cnblogs.com/mfrank/p/11260355.html#autoid-0-5-0
 *
 * TTL是什么呢？TTL是RabbitMQ中一个消息或者队列的属性，表明一条消息或者该队列中的所有消息的最大存活时间，单位是毫秒。
 * 换句话说，如果一条消息设置了TTL属性或者进入了设置TTL属性的队列，那么这条消息如果在TTL设置的时间内没有被消费，
 * 则会成为“死信”。如果同时配置了队列的TTL和消息的TTL，那么较小的那个值将会被使用。
 *
 * 设置TTL的两种方式:
 * 1. 第一种是在创建队列的时候设置队列的“x-message-ttl”属性, 这样所有被投递到该队列的消息都最多不会存活超过6s。
 * 如果设置了队列的TTL属性，那么一旦消息过期，就会被队列丢弃.
 *
 * Map<String, Object> args = new HashMap<String, Object>();
 * args.put("x-message-ttl", 6000);
 * channel.queueDeclare(queueName, durable, exclusive, autoDelete, args);
 *
 * 2. 针对每条消息设置TTL
 * 消息即使过期，也不一定会被马上丢弃，因为消息是否过期是在即将投递到消费者之前判定的，
 * 如果当前队列有严重的消息积压情况，则已过期的消息也许还能存活较长时间。
 *
 * AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
 * builder.expiration("6000");
 * AMQP.BasicProperties properties = builder.build();
 * channel.basicPublish(exchangeName, routingKey, mandatory, properties, "msg body".getBytes());
 *
 * 3. 下面演示的是配置多个延时队列 分别给每个队列设置不停的延时时间 产生的死信分配到不同的死信交换机和死信路由：
 *
 * */
@Configuration
public class TTLConfig {

    public static final String DELAY_EXCHANGE_NAME = "delay.ttl.queue.demo.business.exchange";
    public static final String DELAY_QUEUEA_NAME = "delay.ttl.queue.demo.business.queuea";
    public static final String DELAY_QUEUEB_NAME = "delay.ttl.queue.demo.business.queueb";

    //不设置延时带下的队列C
    public static final String QUEUEC_NAME = "ttl.queue.demo.business.queuec";

    public static final String DELAY_QUEUEA_ROUTING_KEY = "delay.ttl.queue.demo.business.queuea.routingkey";
    public static final String DELAY_QUEUEB_ROUTING_KEY = "delay.ttl.queue.demo.business.queueb.routingkey";
    public static final String DELAY_QUEUEC_ROUTING_KEY = "ttl.queue.demo.business.queuec.routingkey";

    public static final String DEAD_LETTER_EXCHANGE = "delay.ttl.queue.demo.deadletter.exchange";
    public static final String DEAD_LETTER_QUEUEA_NAME = "delay.ttl.queue.demo.deadletter.queuea";
    public static final String DEAD_LETTER_QUEUEB_NAME = "delay.ttl.queue.demo.deadletter.queueb";
    public static final String DEAD_LETTER_QUEUEC_NAME = "delay.ttl.queue.demo.deadletter.queuec";

    public static final String DEAD_LETTER_QUEUEA_ROUTING_KEY = "delay.ttl.queue.demo.deadletter.delaya.routingkey";
    public static final String DEAD_LETTER_QUEUEB_ROUTING_KEY = "delay.ttl.queue.demo.deadletter.delayb.routingkey";
    public static final String DEAD_LETTER_QUEUEC_ROUTING_KEY = "delay.ttl.queue.demo.deadletter.delayc.routingkey";


    // 声明延时Exchange
    @Bean("delayExchange")
    public DirectExchange delayExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    // 声明死信Exchange
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    //声明延时队列A 延时10s
    //绑定到对应的死信交换机
    @Bean("delayQueueA")
    public Queue delayQueueA(){

        Map<String, Object> args = new HashMap<>();

        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);

        //x-dead-letter-routing-key 这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEA_ROUTING_KEY);

        //x-message-ttl 声明队列的ttl 1000表示1s 测试的时候注意在后端删除Queue再试
        args.put("x-message-ttl", 1000*10);

        return QueueBuilder.durable(DELAY_QUEUEA_NAME).withArguments(args).build();
    }

    //声明延时队列B 延时20s
    @Bean("delayQueueB")
    public Queue delayQueueB(){

        Map<String, Object> args = new HashMap<>();

        //x-dead-letter-exchange
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);

        //x-dead-letter-routing-key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEB_ROUTING_KEY);

        //x-message-ttl 1000表示1s
        args.put("x-message-ttl", 1000*20);

        return QueueBuilder.durable(DELAY_QUEUEB_NAME).withArguments(args).build();
    }

    // 声明不设置队列延迟时间的队列
    @Bean("queueC")
    public Queue queueC(){

        Map<String, Object> args = new HashMap<>(2);

        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);

        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEC_ROUTING_KEY);

        return QueueBuilder.durable(QUEUEC_NAME).withArguments(args).build();
    }

    // 声明死信队列A 用于接收延时10s处理的消息
    @Bean("deadLetterQueueA")
    public Queue deadLetterQueueA(){

        return new Queue(DEAD_LETTER_QUEUEA_NAME);
    }

    // 声明死信队列B  用于接收延时20s处理的消息
    @Bean("deadLetterQueueB")
    public Queue deadLetterQueueB(){

        return new Queue(DEAD_LETTER_QUEUEB_NAME);
    }

    // 声明死信队列C 用于接收延时任意时长的消息
    @Bean("deadLetterQueueC")
    public Queue deadLetterQueueC(){

        return new Queue(DEAD_LETTER_QUEUEC_NAME);
    }

    // 声明延时队列A绑定关系
    @Bean
    public Binding delayBindingA(@Qualifier("delayQueueA") Queue delayQueueA,
                                 @Qualifier("delayExchange") DirectExchange delayExchange){

        return BindingBuilder.bind(delayQueueA).to(delayExchange).with(DELAY_QUEUEA_ROUTING_KEY);
    }

    // 声明延时队列B绑定关系
    @Bean
    public Binding delayBindingB(@Qualifier("delayQueueB") Queue delayQueueB,
                                 @Qualifier("delayExchange") DirectExchange delayExchange){

        return BindingBuilder.bind(delayQueueB).to(delayExchange).with(DELAY_QUEUEB_ROUTING_KEY);
    }

    // 声明延时队列C绑定关系
    @Bean
    public Binding delayBindingC(@Qualifier("queueC") Queue queueC,
                                 @Qualifier("delayExchange") DirectExchange delayExchange){

        return BindingBuilder.bind(queueC).to(delayExchange).with(DELAY_QUEUEC_ROUTING_KEY);
    }

    // 声明死信队列A绑定关系
    @Bean
    public Binding deadLetterBindingA(@Qualifier("deadLetterQueueA") Queue deadLetterQueueA,
                                      @Qualifier("deadLetterExchange") DirectExchange deadLetterExchange){

        return BindingBuilder.bind(deadLetterQueueA).to(deadLetterExchange).with(DEAD_LETTER_QUEUEA_ROUTING_KEY);
    }

    // 声明死信队列B绑定关系
    @Bean
    public Binding deadLetterBindingB(@Qualifier("deadLetterQueueB") Queue deadLetterQueueB,
                                      @Qualifier("deadLetterExchange") DirectExchange deadLetterExchange){

        return BindingBuilder.bind(deadLetterQueueB).to(deadLetterExchange).with(DEAD_LETTER_QUEUEB_ROUTING_KEY);
    }

    // 声明死信队列C绑定关系
    @Bean
    public Binding deadLetterBindingC(@Qualifier("deadLetterQueueC") Queue deadLetterQueueC,
                                      @Qualifier("deadLetterExchange") DirectExchange deadLetterExchange){

        return BindingBuilder.bind(deadLetterQueueC).to(deadLetterExchange).with(DEAD_LETTER_QUEUEC_ROUTING_KEY);
    }
}
