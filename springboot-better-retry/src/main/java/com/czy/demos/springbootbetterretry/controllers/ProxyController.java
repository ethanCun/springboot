package com.czy.demos.springbootbetterretry.controllers;

import com.czy.demos.springbootbetterretry.config.proxy.HelloRetryProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    @Autowired
    private HelloRetryProxyService helloRetryProxyService;

    @RequestMapping(value = "/proxy")
    public String proxySay(String sth){

        return helloRetryProxyService.saySth(sth);
    }
}
