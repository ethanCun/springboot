package com.example.demo.controller;

import com.example.demo.common.CzyAdviceAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CzyAdviceAnnotation
public class HelloController {

    @GetMapping(value = "/hello1")
    public String hello(String name){

        return "hello1 " + name;
    }

    @GetMapping(value = "/hello11")
    public String hello11(String name){

        return "hello11 " + name;
    }

    @GetMapping(value = "/hello12")
    public String hello12(String name){

        return "hello12 " + name;
    }

}
