package com.example.demoaoplog.controller;

import com.example.demoaoplog.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping("/")
public class Controller1 {

    @ResponseBody
    @GetMapping(value = "/aa")
    public String aa(HttpServletRequest request){

        log.debug("走了这里-----------debug");
        log.info("走了这里-----------info");
        log.warn("走了这里-----------warn");
        log.error("走了这里-----------error");

        return "aa";
    }

    @ResponseBody
    @GetMapping(value = "/log1")
    public String log1(String username, String password){

        return username + ":" + password;
    }

    @ResponseBody
    @PostMapping(value = "/log12")
    public User log12(@RequestBody User user){

        return user;
    }

    @ResponseBody
    @PostMapping(value = "/log13")
    public HashMap<String, Object> log13(@RequestBody HashMap<String, Object> map){

        return map;
    }
}
