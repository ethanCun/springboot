package com.example.swaggerui.demo.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@RestController
public class DevController {

    @GetMapping(value = "/dev")
    public String dev(){

        return "dev";
    }
}
