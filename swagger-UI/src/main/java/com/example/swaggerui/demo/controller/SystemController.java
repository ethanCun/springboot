package com.example.swaggerui.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"系统管理"})
@RestController
public class SystemController {

    @ApiOperation("获取系统信息")
    @GetMapping(value = "/systemInfo")
    public String systemInfo(){

        return "systemInfo";
    }
}
