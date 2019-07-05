package com.example.demo.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.app.entity.User;
import com.example.demo.common.ExceptionHandler.CustomException;
import com.example.demo.common.ExceptionHandler.CustomExceptionEnum;
import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor;

import java.util.Date;

//自定义token生成器 绑定用户信息和登录时绑定的时间
public class TokenHelper {

    private User user;
    //用于根据时间产生不同的token 每次登录替换成新的token
    private Long time = System.currentTimeMillis();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public TokenHelper() {
    }

    public TokenHelper(User user, Long time) {
        this.user = user;
        this.time = time;
    }

    //生成token： user转json字符串 aes加密
    public static String generateToken(TokenHelper tokenHelper){

        String tokenJson = JSON.toJSONString(tokenHelper);

        try {

            //AES对tokenJson机密
            return EncryptHelper.encrypt(tokenJson);
        }catch (Exception e){

            throw new CustomException(CustomExceptionEnum.EncryptError);
        }
    }

    //由token取出用户信息
    public static TokenHelper getUserWithToken(String token){

        if (token == null || "".equals(token)){

            throw new CustomException(CustomExceptionEnum.TokenIsNull);
        }

        //对token解密
        String tokenJson = "";

        try {

            tokenJson = EncryptHelper.decrypt(token);

        }catch (Exception e){

            throw new CustomException(CustomExceptionEnum.TokenValidateFailed);
        }


        return (TokenHelper)JSON.parseObject(tokenJson, TokenHelper.class);
    }
}
