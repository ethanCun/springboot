package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 在springboot项目里当我们在控制器类上加上@RestController注解或者其内的方法上加入@ResponseBody注解后，
 * 默认会使用jackson插件来返回json数据，下面我们利用fastjson为我们提供的FastJsonHttpMessageConverter来返回json数据。
 * */

//更多关于FastJson的使用：https://segmentfault.com/a/1190000011212806

@Controller
public class UserController {

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String home(){

        return "home";
    }

    @ResponseBody
    @GetMapping(value = "/get")
    public Object getList(){

        List<UserEntity> list = new ArrayList<>();
        UserEntity userEntity = new  UserEntity(null, "changsha", new Date());

        list.add(userEntity);

        return list;
    }

    @ResponseBody
    @GetMapping(value = "/testFastJson")
    public void testFastJson(){

        //object -> str
        UserEntity userEntity = new UserEntity("zhangsan", "hunan", new Date());

        //date会转化为时间戳
        String s1 = JSONObject.toJSONString(userEntity);

        System.out.println("s1 = " + s1);

        //str -> JSONObject
        JSONObject jsonObject = JSONObject.parseObject(s1);

        System.out.println(jsonObject.getString("name"));
        System.out.println(jsonObject.getString("address"));
        System.out.println(jsonObject.getDate("date"));

        //str -> object
        UserEntity userEntity1 = JSONObject.parseObject(s1, UserEntity.class);
        System.out.println("user1 = " + userEntity1);
    }
}
