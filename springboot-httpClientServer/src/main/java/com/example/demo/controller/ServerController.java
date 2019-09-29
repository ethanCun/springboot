package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    //不带参数的简单get请求
    @RequestMapping(value = "/get")
    public String get(){

        return "response by get method";
    }

    //带简单参数的get请求
    @RequestMapping(value = "/getWithSimpleParam")
    public String getWithSimpleParam(String name){

        return name;
    }

    //带实体的get请求
    @RequestMapping(value = "/getWithEntityParam")
    public User getWithEntityParam(User user){

        return user;
    }

    @PostMapping(value = "/post")
    public String post(){

        return "response by post method";
    }

    //post：实体参数 返回实体
    @PostMapping(value = "/postWithEntity")
    public User postWithEntity(@RequestBody User user){

        return user;
    }
}
