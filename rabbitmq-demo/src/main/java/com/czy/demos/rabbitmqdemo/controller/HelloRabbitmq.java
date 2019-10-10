package com.czy.demos.rabbitmqdemo.controller;

import com.czy.demos.rabbitmqdemo.config.DirectExchange.PayNotifySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloRabbitmq {

    @Autowired
    private PayNotifySender sender;

    @ResponseBody
    @RequestMapping(value = "/send")
    public void send(String msg){

        sender.sender(msg);
    }
}
