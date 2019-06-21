package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/home")
    public String home(){

        return "home";
    }

    @GetMapping(value = "/layui-tab")
    public String layuitab(){

        return "layui-tab";
    }

    @GetMapping(value = "/nav")
    public String nav(){

        return "nav";
    }

    @GetMapping(value = "/breadcrumb")
    public String breadcrumb(){

        return "breadcrumb";
    }

    @GetMapping(value = "/grid")
    public String grid(){

        return "grid";
    }

    @GetMapping(value = "/datepicker")
    public String datepicker(){

        return "DatePicker";
    }
}
