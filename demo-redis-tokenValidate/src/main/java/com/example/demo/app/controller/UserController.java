package com.example.demo.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.app.entity.User;
import com.example.demo.app.service.UserService;
import com.example.demo.common.Annotation.RequireToken;
import com.example.demo.common.Base.BaseController;
import com.example.demo.common.Base.BaseModel;
import com.example.demo.common.utils.EncryptHelper;
import com.example.demo.common.utils.TokenHelper;
import org.apache.shiro.codec.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.applet.Main;
import sun.misc.BASE64Encoder;
import sun.tools.jstat.Token;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


@Controller
@RequestMapping(value = "/api/user")
@EnableCaching
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequireToken(require = true)
    @ResponseBody
    @RequestMapping(value = "/test")
    public BaseModel test(){

        return BaseModel.success("成功方位test");
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public BaseModel login(@Validated User user){

        return this.userService.loginWithUser(user);
    }


}
