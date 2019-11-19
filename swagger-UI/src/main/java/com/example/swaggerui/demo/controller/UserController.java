package com.example.swaggerui.demo.controller;

import com.example.swaggerui.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"用户管理"})
@RestController
public class UserController {

    @ApiOperation("获取用户姓名")
    @ApiImplicitParam(name = "user", value = "用户信息", dataType = "User", paramType = "body")
    @RequestMapping(value = "/getUserName", method = RequestMethod.POST)
    public String getUserName(@RequestBody User user){

        return user.getName();
    }
}
