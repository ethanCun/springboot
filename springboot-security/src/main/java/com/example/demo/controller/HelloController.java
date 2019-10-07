package com.example.demo.controller;

import com.example.demo.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser(String username, String password){

        System.out.println("username = " + username + " password = " + password);

        SysUser user = new SysUser();
        user.setUsername(username);

        //对密码进行加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public int login(String username, String password){

        return 0;
    }

    @RequestMapping(value = "/login")
    public String login(){

        return "login";
    }

    @RequestMapping(value = "/register")
    public String register(){

        return "register";
    }

    @RequestMapping(value = "/index")
    public String index(){

        return "index";
    }

    // /error 是系统默认的映射  不能重名 否则报错：Ambiguous mapping. Cannot map 'basicErrorController' method
    @RequestMapping(value = "/myerror")
    public String error(){

        return "error";
    }

    @ResponseBody
    @RequestMapping(value = "/admin")
    public String admin(){

        return "hello admin";
    }

    @ResponseBody
    @RequestMapping(value = "/test")
    public String test(){

        return "hello test";
    }
}
