package com.example.demo.controller;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.Page;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "demo")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "/table/user")
    public Map<String, Object> users(HttpServletRequest request, @RequestParam Map<String, String> param){

        Map<String, Object> map = new HashMap<>();

        if ((param == null) || (Integer.parseInt(param.get("page")) <= 0) || Integer.parseInt(param.get("limit")) <= 0){

            map.put("code", -1);
            map.put("message", "page和index必传");
            map.put("data", null);
            map.put("count", 0);

            return map;
        }

        PageInfo<User> pageInfo = userService.findUserList(request, param);


        map.put("code", 0);
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        map.put("msg", "success");

        return map;
    }

//1.ajax提交json数据的时候，需要使用JSON.stringify对数据进行格式化为字符串，并且设定contentType: “application/json”
//  ajax提交form请求的时候，传送的是json对象，而不是json字符串

//2.使用json提交数据的时候，后台接收数据需要使用 @RequestBody注解来接收，而使用 form 提交的时候不需要任何注解。


//    接受前端的ajax form请求 data传json对象
    @ResponseBody
    @GetMapping(value = "/table/user2")
    public Map<String, Object> users2(HttpServletRequest request, Page page){

        Map<String, Object> map = new HashMap<>();

        if ((page == null) || (page.getPage() <= 0) ||( page.getLimit() <= 0)){

            map.put("code", -1);
            map.put("message", "page和index必传");
            map.put("data", null);
            map.put("count", 0);

            return map;
        }

        PageInfo<User> pageInfo = userService.findUserList(request, page);

        map.put("code", 0);
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        map.put("msg", "success");

        return map;
    }

//接受前端的ajax json请求 data传json字符串
    @ResponseBody
    @PostMapping(value = "/table/user3")
    public Page user3(@RequestBody Page page){

        return page;
    }
}
