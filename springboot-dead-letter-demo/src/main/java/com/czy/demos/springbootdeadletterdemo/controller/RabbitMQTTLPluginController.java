package com.czy.demos.springbootdeadletterdemo.controller;

import com.czy.demos.springbootdeadletterdemo.RabbitmqTTLWithPlugin.DelayedMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQTTLPluginController {

    @Autowired
    private DelayedMessageSender sender;

    @RequestMapping(value = "/test4")
    public void send(String msg, Integer seconds){

        this.sender.send(msg, seconds);
    }
}
