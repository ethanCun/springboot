package com.example.demoaoplog.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class Controller2 {

    @ResponseBody
    @GetMapping(value = "/log2")
    public String log1(String username, String password){

        return username + ":" + password;
    }
}
