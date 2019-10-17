package com.czy.demos.springbootbetterretry.NormalRetry.Imp;

import com.czy.demos.springbootbetterretry.NormalRetry.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("cglibProxyNoInterfaceImp")
public class CglibProxyNoInterfaceImp {

    @Autowired
    private DependencyService dependencyService;

    public String listening(String sth){

        //测试依赖第三者bean 也会报错  因为代理对象中并没有实例化该bean 注入会失败
        //dependencyService.eat(sth);

        System.out.println("listening sth1: " + sth);

        return "listening sth1: " + sth;
    }
}
