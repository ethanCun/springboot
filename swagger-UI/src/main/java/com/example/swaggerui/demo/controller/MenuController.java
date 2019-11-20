package com.example.swaggerui.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"菜单管理"})
@ApiIgnore
@RestController
public class MenuController {

    @ApiOperation("菜单管理menu")
    @GetMapping(value = "/menu")
    public String menu(){

        return "menu";
    }
}
