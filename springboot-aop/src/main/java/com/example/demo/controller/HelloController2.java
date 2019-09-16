package com.example.demo.controller;

import com.example.demo.common.CzyAdviceAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController2 {

    @CzyAdviceAnnotation
    @GetMapping(value = "/hello2")
    public String hello(String name){

        return "hello2 " + name;
    }



}
