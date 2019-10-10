package com.czy.demos.rabbitmqdemo.config.FanoutExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FanoutExchange交换机是转发消息到所有绑定队列（广播模式，和routingKey没有关系）。
 */
@Configuration
public class FanoutExchangeConfig {

    //下面是将两个消息队列绑定到同一个FanoutExchange交换机
    @Bean
    public Queue reportPaymentQueue(){

        return new Queue("api.report.payment");
    }

    @Bean
    public Queue reportRefundQueue(){

        return new Queue("api.report.refund");
    }

    @Bean
    public FanoutExchange reportExchange(){

        return new FanoutExchange("reportExchange");
    }

    @Bean
    public Binding bindingReportPaymentExchange(Queue reportPaymentQueue, FanoutExchange reportExchange){

        //广播模式  不需要在去匹配什么
        return BindingBuilder.bind(reportPaymentQueue).to(reportExchange);
    }

    @Bean
    public Binding bindingReportRefundExchange(Queue reportRefundQueue, FanoutExchange reportExchange){

        //广播模式  不需要在去匹配什么
        return BindingBuilder.bind(reportRefundQueue).to(reportExchange);
    }
}
