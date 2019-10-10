package com.czy.demos.rabbitmqdemo.config.HedaersExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * HeadersExchange交换机是根据请求消息中设置的header attribute参数类型来匹配的（和routingKey没有关系）。
 * 因此HeaderExchange发送的消息 需要绑定headerValues, 消息对象为Message。
 * */
@Configuration
public class HeadersExchangeConfig {

    @Bean
    public Queue creditBankQueue(){

        return new Queue("credit.bank");
    }

    @Bean
    public Queue creditFinanceQueue(){

        return new Queue("credit.finance");
    }

    @Bean
    public HeadersExchange creditBankExchange(){

        return new HeadersExchange("creditBankExchange");
    }

    @Bean
    public HeadersExchange creditFinanceExchange(){

        return new HeadersExchange("creditFinanceExchange");
    }

    //绑定
    @Bean
    public Binding bindingCreditAExchange(Queue creditBankQueue, HeadersExchange creditBankExchange){

        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");

        //creditBankExchange交换机的匹配规则是完全匹配，即header attribute参数必须完成一致， 不完全匹配不会被消费。
        //如：testHeadersExchangeCreditBank1
        return BindingBuilder.bind(creditBankQueue).to(creditBankExchange).whereAll(headerValues).match();
    }

    @Bean
    public Binding bindingCreditBExchange(Queue creditFinanceQueue,
                                                 HeadersExchange creditFinanceExchange){

        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");

        //creditFinanceExchange可以匹配一个或者多个 都会被消费
        return BindingBuilder.bind(creditFinanceQueue).to(creditFinanceExchange).whereAny(headerValues).match();
    }
}
