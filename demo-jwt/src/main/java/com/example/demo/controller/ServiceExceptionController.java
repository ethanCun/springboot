package com.example.demo.controller;

import com.example.demo.Service.UserService;
import com.example.demo.common.utils.ByteConvertHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping(value = "/api/serviceExceptionHandle")
public class ServiceExceptionController {

    @Autowired
    private UserService userService;

    //尝试捕捉service层的异常
    @RequestMapping(value = "/testThrowExceptionAtServiceLayer")
    public void testThrowExceptionAtServiceLayer(){

        userService.testThrowExceptionAtServiceLayer();
    }
}
