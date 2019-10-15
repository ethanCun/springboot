package com.czy.demos.springbootdeadletterdemo.controller;

import com.czy.demos.springbootdeadletterdemo.config.BusinessMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQMsgController {

//    @Autowired
//    private BusinessMessageSender sender;
//
//    @RequestMapping(value = "/sendmsg")
//    public String sendmsg(String msg){
//
//        sender.sendMsg(msg);
//
//        return msg;
//    }
}
