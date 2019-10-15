package com.czy.demos.springbootdeadletterdemo.controller;

import com.czy.demos.springbootdeadletterdemo.pojo.DelayTypeEnum;
import com.czy.demos.springbootdeadletterdemo.rabbitmqTTL.DelayMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RabbitMQTTLController {

    @Autowired
    private DelayMessageSender sender;

    @RequestMapping(value = "/helloworld")
    public String helloworld(){
        return "helloworld";
    }

    @RequestMapping(value = "/test1")
    public void send10s(String msg){

        System.out.println("msg = " + msg);

        sender.sendWithQueueTTL(msg, DelayTypeEnum.DELAY_10S);
    }

    @RequestMapping(value = "/test2")
    public void send20s(String msg){

        sender.sendWithQueueTTL(msg, DelayTypeEnum.DELAY_20S);
    }

    @RequestMapping(value = "/test3")
    public void send5s(String msg, Integer seconds){

        sender.sendWithMessageTTL(msg, seconds);
    }
}
