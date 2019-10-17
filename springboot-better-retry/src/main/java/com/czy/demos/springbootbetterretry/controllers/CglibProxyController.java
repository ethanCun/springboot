package com.czy.demos.springbootbetterretry.controllers;

import com.czy.demos.springbootbetterretry.NormalRetry.HelloService;
import com.czy.demos.springbootbetterretry.NormalRetry.Imp.CglibProxyNoInterfaceImp;
import com.czy.demos.springbootbetterretry.NormalRetry.Imp.CglibProxyNoInterfaceImp2;
import com.czy.demos.springbootbetterretry.NormalRetry.Imp.HelloServiceImp;
import com.czy.demos.springbootbetterretry.config.CGLIBProxy.CGLIBProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CglibProxyController {

    //没有实现接口的两个类
    @Autowired
    private CglibProxyNoInterfaceImp cglibProxyNoInterfaceImp;
    @Autowired
    private CglibProxyNoInterfaceImp2 cglibProxyNoInterfaceImp2;

    //实现接口的类
    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/cglibProxy")
    public String listen(String sth){

        //不使用代理测试 在一个service中注入另外一个service的bean是否成功 测试结果是成功 但是使用代理时注入会失败
        //报错：null
        this.cglibProxyNoInterfaceImp.listening("czy...czy");

        //没有实现接口的类
        CglibProxyNoInterfaceImp childProxy = (CglibProxyNoInterfaceImp)new CGLIBProxy().getProxy(CglibProxyNoInterfaceImp.class);
        childProxy.listening(sth);

        CglibProxyNoInterfaceImp2 childProxy2 = (CglibProxyNoInterfaceImp2)new CGLIBProxy().getProxy(CglibProxyNoInterfaceImp2.class);
        childProxy2.listening(sth);

        //实现接口的类
        HelloServiceImp helloServiceImp = (HelloServiceImp)new CGLIBProxy().getProxy(HelloServiceImp.class);
        helloServiceImp.saySth(sth);

        return sth;
    }
}
