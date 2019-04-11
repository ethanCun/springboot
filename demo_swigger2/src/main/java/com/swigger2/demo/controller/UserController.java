package com.swigger2.demo.controller;

import com.swigger2.demo.entity.User;
import com.swigger2.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

//----------------Swagger@文档地址 # Swagger的port和address与application.yml中的一致---------------
//http://localhost:8080/swagger-ui.html
//Swagger注解详细说明：https://blog.csdn.net/u014231523/article/details/76522486

@RestController
@ResponseBody
@RequestMapping("/user")
@Api(value = "UserController相关的API", tags = {"用户操作相关的接口"}, description = "用户信息描述字段：description")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询", notes = "获取所有的用户信息")
    @GetMapping("/fetchAllUser")
    public List<User> fetchAllUser(){

        return userService.fetchAllUser();
    }

    @ApiOperation(value = "查询", notes = "根据id查找用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "Integer")
    })
    @GetMapping("/getUserWithId")
    public User getUserWithId(int id){

        return userService.getUserWithId(id);
    }

    @ApiOperation(value = "新增", notes = "新增一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "age", value = "用户年龄", required = false, dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "updateDate", value = "更新时间", required = false, dataType = "String")
    })
    @PostMapping("/insertUser")
    public int insertUser(User user){

        return userService.insertUser(user);
    }

    @ApiOperation(value = "删除", notes = "根据id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "Integer")
    @PostMapping("/deleteUserWithId")
    public int deleteUserWithId(int id){

        return userService.deleteUserWithId(id);
    }

    @ApiOperation(value = "更新", notes = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "age", value = "用户年龄", required = false, dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "updateDate", value = "更新时间", required = false, dataType = "String")
    })
    @PostMapping("/updateUser")
    public int updateUser(User user){

        return userService.updateUser(user);
    }
}
