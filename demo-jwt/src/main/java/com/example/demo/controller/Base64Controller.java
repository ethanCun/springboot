package com.example.demo.controller;

import com.example.demo.common.utils.ByteConvertHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping(value = "/api/base64")
public class Base64Controller {

    //base64加密与解密
    @RequestMapping(value = "/testBase64Encode")
    public String testBase64Encode(String content){

        return Base64.getEncoder().encodeToString(ByteConvertHelper.Object2Byte(content));
    }

    @ResponseBody
    @RequestMapping(value = "testBase64Decode")
    public Object testBase64Decode(String str){

        return ByteConvertHelper.bytes2Object(Base64.getDecoder().decode(str));
    }
}
