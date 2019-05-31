package com.example.demo.controller;

import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home")
    public String home(){

        return "home";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllUserInfos")
    public Map<String, Object> getAllUserInfos(){

        Map<String, Object> map = new HashMap<>();
        map.put("message", "success");
        map.put("code", 0);
        map.put("data", userService.getAllUserInfos());

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfoWithId")
    public UserInfo getUserInfoWithId(int id){

        return userService.getUserInfoWithId(id);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUserInfoWithId")
    public int deleteUserInfoWithId(int id){

        return userService.deleteUserInfoWithId(id);
    }
}
