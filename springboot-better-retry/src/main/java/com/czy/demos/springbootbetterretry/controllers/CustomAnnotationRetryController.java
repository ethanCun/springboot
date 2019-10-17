package com.czy.demos.springbootbetterretry.controllers;

import com.czy.demos.springbootbetterretry.Exception.CzyException;
import com.czy.demos.springbootbetterretry.NormalRetry.HelloService;
import com.czy.demos.springbootbetterretry.config.CustomAnnotationRetry.CustomRetryAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CustomAnnotationRetryController {

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping(value = "/annotionRetry")
    @CustomRetryAnnotation(retryTimes = 10)
    public void customAnnotationRetry(){

        this.helloService.saySth("12121212111");

    }

    @ResponseBody
    @RequestMapping(value = "/test11")
    public void testThrowException(){

        throw new CzyException("rpc调用失败");
    }

    @ResponseBody
    @RequestMapping(value = "/test22")
    public void testThrowException2(){

        this.helloService.saySth("12121212111");
    }

    @RequestMapping(value = "/test33")
    public void testThrowException3() throws Exception {

        throw new Exception("指向错误页面");
    }

    @RequestMapping(value = "/czyerror")
    public String czyerror(){

        return "czyerror";
    }
}
