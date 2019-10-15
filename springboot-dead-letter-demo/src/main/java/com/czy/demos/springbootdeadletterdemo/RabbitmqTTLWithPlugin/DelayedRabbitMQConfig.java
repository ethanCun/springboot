package com.czy.demos.springbootdeadletterdemo.RabbitmqTTLWithPlugin;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayedRabbitMQConfig {

    public static final String DELAYED_QUEUE_NAME = "ttl.delayed.demo.queue";
    public static final String DELAYED_EXCHANGE_NAME = "ttl.delayed.demo.exchange";
    public static final String DELAYED_ROUTING_KEY_NAME = "ttl.delayed.demo.routing_key";

    @Bean(name = "delayedQueue")
    public Queue delayedQueue(){

        return new Queue(DELAYED_QUEUE_NAME);
    }

    @Bean(name = "customExchange")
    public CustomExchange customExchange(){

        Map<String, Object> params = new HashMap<>();

        params.put("x-delayed-type", "direct");

        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message",
                true, false, params);
    }

    @Bean
    public Binding delayedBinding(@Qualifier("delayedQueue") Queue delayedQueue,
                                  @Qualifier("customExchange") CustomExchange customExchange){

        return BindingBuilder.bind(delayedQueue).to(customExchange).with(DELAYED_ROUTING_KEY_NAME).noargs();
    }
}
