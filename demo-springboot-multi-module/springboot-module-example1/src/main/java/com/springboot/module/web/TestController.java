package com.springboot.module.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test/aa")
    public String aa(){
        return "aa";
    }
}
