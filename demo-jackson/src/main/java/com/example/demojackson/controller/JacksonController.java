package com.example.demojackson.controller;

import com.example.demojackson.dao.UserMapper;
import com.example.demojackson.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.DataOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class JacksonController {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    @ResponseBody
    @GetMapping(value = "/users")
    public List<User> users1(){

        return this.userMapper.selectAll();
    }

    @ResponseBody
    @GetMapping(value = "/add")
    public int addUser(User user){

        return this.userMapper.insert(user);
    }

    public static void main(String[] args) throws Exception {

        User user = new User();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setBirthday(new Date());
        user.setUsername("张三");
        user.setAge(20);

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        objectMapper.writeValue(new File("/Users/cunethan/Desktop/user.json"), user);

        User newUser = objectMapper.readValue(userJson, User.class);

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = dateTimeFormat.format(new Date());
        objectMapper.setDateFormat(dateTimeFormat) ;

    }
}
