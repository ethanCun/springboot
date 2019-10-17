package com.czy.demos.springbootbetterretry.controllers;

import com.czy.demos.springbootbetterretry.NormalRetry.DependencyService;
import com.czy.demos.springbootbetterretry.NormalRetry.HelloService;
import com.czy.demos.springbootbetterretry.NormalRetry.Imp.DependencyServiceImp;
import com.czy.demos.springbootbetterretry.NormalRetry.Imp.HelloServiceImp;
import com.czy.demos.springbootbetterretry.config.DynamicProxy.DynamicProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicProxyController {


    @RequestMapping(value = "/dynamicProxy")
    public void dynamicProxy(String sth){

        //interface
        HelloService helloService = new HelloServiceImp();
        //根据interface获取代理对象
        HelloService dynamicProxy = (HelloService)DynamicProxy.getProxy(helloService);
        //代理对象发起调用
        dynamicProxy.saySth(sth);

        DependencyService dependencyService = new DependencyServiceImp();
        DependencyService dependencyServiceProxy = (DependencyService)DynamicProxy.getProxy(dependencyService);
        dependencyServiceProxy.eat(sth);
    }
}
