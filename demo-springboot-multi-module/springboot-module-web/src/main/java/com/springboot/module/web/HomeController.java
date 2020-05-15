package com.springboot.module.web;

import com.springboot.module.entity.User;
import com.springboot.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    public List<User> userList(){

        return this.userService.userList();
    }
}
