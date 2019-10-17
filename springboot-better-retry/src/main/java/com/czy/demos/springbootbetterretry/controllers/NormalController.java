package com.czy.demos.springbootbetterretry.controllers;

import com.czy.demos.springbootbetterretry.NormalRetry.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NormalController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hello")
    public String hello(String something){

        return helloService.saySth(something);
    }
}
