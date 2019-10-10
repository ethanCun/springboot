package com.czy.demos.rabbitmqdemo.config.TopicExchange;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * TopicExchange是按规则转发消息，是交换机中最灵活的一个。也是最常用的一个。
 *
 * TopicExchange交换机支持使用通配符*、#: *号只能向后多匹配一层路径，#号可以向后匹配多层路径。
 * 配置一个routingKey为api.core的消息队列并绑定在coreExchange交换机上（交换机的匹配规则为api.core.*）
 * 配置一个routingKey为api.payment的消息队列并绑定在paymentExchange交换机上（交换机的匹配规则为api.payment.#）
 */
@Configuration
public class TopicExchangeConfig {

    //实例化两个消息队列bean
    @Bean
    public Queue coreQueue(){

        return new Queue("api.core");
    }

    @Bean
    public Queue paymentQueue(){

        return new Queue("api.payment");
    }

    //实例化两个交换机
    @Bean
    public TopicExchange coreExchange(){

        return new TopicExchange("coreExchange");
    }

    @Bean
    public TopicExchange paymentExchange(){

        return new TopicExchange("paymentExchange");
    }

    /**
     * TopicExchange交换机支持使用通配符*、#:
     *
     * *号只能向后多匹配一层路径。 #号可以向后匹配多层路径。
     *
     * 因此routingkey为api.core.user.query多层级的是不能被消费的
     * routingkey为api.payment.order.detail多层级是可以被消费的
     * */

    //将对应的bean绑定到对应的交换机上
    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange){

        return BindingBuilder.bind(coreQueue).to(coreExchange).with("api.core.*");
    }

    @Bean
    public Binding bindingPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange){

        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
    }
}
