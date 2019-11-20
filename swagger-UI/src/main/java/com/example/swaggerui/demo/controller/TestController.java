package com.example.swaggerui.demo.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("test")
@RestController
public class TestController {

    @GetMapping(value = "/atest")
    public String test(){

        return "test";
    }
}
