package com.example.shiro.demo9.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @RequestMapping(value = "/userList")
    @RequiresPermissions("userInfo:view")
    public String userInfo(){

        return "userInfo";
    }

    @RequestMapping(value = "/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd(){

        return "userInfoAdd";
    }

    @RequestMapping(value = "/userDelete")
    @RequiresPermissions("userInfo:del")
    public String userInfoDelete(){

        return "userInfoDelete";
    }
}
