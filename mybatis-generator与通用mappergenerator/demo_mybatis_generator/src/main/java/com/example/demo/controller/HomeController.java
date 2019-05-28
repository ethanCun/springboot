package com.example.demo.controller;

import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String uid){
        return userService.deleteByPrimaryKey(uid);
    }

    @RequestMapping(value = "/insert")
    public int insert(UserInfo record){
        return userService.insert(record);
    }

    @RequestMapping(value = "/selectByPrimaryKey")
    public UserInfo selectByPrimaryKey(String uid){
        return userService.selectByPrimaryKey(uid);
    }

    @RequestMapping(value = "/selectAll")
    public List<UserInfo> selectAll(){
        return userService.selectAll();
    }

    @RequestMapping(value = "/updateByPrimaryKey")
    public int updateByPrimaryKey(UserInfo record){
        return userService.updateByPrimaryKey(record);
    }
}
