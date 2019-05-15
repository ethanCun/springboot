package com.example.demo.web;

import com.example.demo.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.NamingEnumeration;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @GetMapping(value = {"", "/", "/home"})
    public String home(Model model){

        model.addAttribute("name", "czy");
        model.addAttribute("age", 27);

        List<String> names = new ArrayList<>();
        names.add("zhangsan");
        names.add("lisi");
        names.add("wangwu");

        model.addAttribute("names", names);

        User user = new User();
        user.setName("zhangsan");
        user.setAge(28);

        model.addAttribute("user", user);

        return "home";
    }

    @GetMapping(value = "/test1")
    public String test1(){

        return "test1";
    }

    @ResponseBody
    @GetMapping(value = {"/user"})
    public User user(){

        User user = new User();
        user.setName("zhangsan");
        user.setAge(28);

        return user;
    }
}
