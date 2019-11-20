package com.example.swaggerui.demo.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("prod")
@RestController
public class ProdController {

    @GetMapping(value = "/prod")
    public String prod(){

        return "prod";
    }
}
