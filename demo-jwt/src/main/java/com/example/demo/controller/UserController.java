package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.UserService;
import com.example.demo.common.BaseModel;
import com.example.demo.common.annotations.PassToken;
import com.example.demo.common.annotations.UserLoginToken;
import com.example.demo.common.utils.ByteConvertHelper;
import com.example.demo.common.utils.MD5Utils;
import com.example.demo.entity.User;
import com.mysql.cj.log.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.tools.java.ClassPath;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.logging.Logger;

@Validated
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Resource
    private UserService userService;

    //需要验证token的测试接口
    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/test1")
    public String test1(){

        return "test1访问成功";
    }

    //不需要验证token的测试接口
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/test2")
    public String test2(){

        return "test2访问成功";
    }

    @PassToken
    @ResponseBody
    @RequestMapping(value = "/adduser")
    public BaseModel addUser(@Validated User user){

        //用户名不能重复
        User user1 = this.userService.findUserWithUserName(user.getUsername());

        if (user1 != null){

            return new BaseModel(null, "用户名已存在", -1);
        }

        String passwordEncrypt = MD5Utils.encrypt(user.getUsername(), user.getPassword());

        User newUser = new User(0, user.getUsername(), passwordEncrypt, "");

        int result = this.userService.addUser(newUser);

        return BaseModel.success(result, "新增客户成功", 0);
    }

    /**
     * 登录成功，返回数据格式：
     *
     * JWT包含了三部分：
     * Header 头部(标题包含了令牌的元数据，并且包含签名和/或加密算法的类型)
     * {
     *   "alg": "HS256",
     *    "typ": "JWT"
     * }
     * Payload 负载 (类似于飞机上承载的物品)
     * Signature 签名/签证
     *
     * {
     *     "code": 0,
     *     "data": {
     *         "id": "1",
     *         "username": "czy",
     *         "password": "000000"
     *     },
     *     "message": "登录成功",
     *     "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIn0.4XopVJlTo9o9YFz4Cmf4Mc9lFYxu1cji_dNNfFNTYg4"
     * }
     *
     * */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/login")
    public BaseModel login(User user){

        User user1 = this.userService.findUserWithUserName(user.getUsername());

        if (user1 == null){

            return BaseModel.success(null, "用户名不存在", -1);

        }else{

            if (!(MD5Utils.encrypt(user.getUsername(), user.getPassword())
                    .equals(user1.getPassword()))){

                return BaseModel.success(null, "密码错误", -1);

            }else {

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", user1.getUsername());
                jsonObject.put("password", user.getPassword());

                String token = user.getToken(user1);
                jsonObject.put("token", token);

                //将token存入本地数据库 其他设备登录 则从新生成一个token  一次只能在一台设备上登录
                int result = this.userService.updateToken(user1.getId(), token);

                return BaseModel.success(jsonObject, "登录成功", 0);
            }
        }
    }

}
