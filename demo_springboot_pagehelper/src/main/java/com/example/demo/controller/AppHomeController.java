package com.example.demo.controller;

import com.example.demo.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppHomeController {

    @Autowired
    private AppService appService;

    @GetMapping(value = "/home")
    public String appHome(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                          Model model){

        model.addAttribute("apps", appService.getAppList(pageNo, pageSize));

        System.out.println(appService.getAppList(pageNo, pageSize));

        return "AppHome";
    }

    @ResponseBody
    @GetMapping(value = "/home2")
    public String appHome2(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                          Model model){

        model.addAttribute("apps", appService.getAppList(pageNo, pageSize));

        System.out.println(appService.getAppList(pageNo, pageSize));

        return "AppHome";
    }
}
