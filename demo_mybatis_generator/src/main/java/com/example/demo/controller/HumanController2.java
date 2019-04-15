package com.example.demo.controller;

import com.example.demo.entity.Human;
import com.example.demo.entity.THuman;
import com.example.demo.service.HumanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "mybatis-generator", description = "mybatis-generator:human相关api")
@RestController
@RequestMapping(value = "/test2")
public class HumanController2 {

    @Autowired
    private HumanService humanService;

    @ApiOperation(value = "查询", notes = "所有信息")
    @GetMapping(value = "/selectAll")
    public List<THuman> selectAll(){

        return humanService.selectAll();
    }

    @ApiOperation(value = "查询", notes = "根据id查询")
    @GetMapping(value = "/selectByPrimaryKey")
    public THuman selectByPrimaryKey(int id){

        return humanService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "插入")
    @PostMapping(value = "/insert")
    public int insert(THuman human){

        return humanService.insert(human);
    }

    @ApiOperation(value = "更新")
    @PostMapping(value = "/updateByPrimaryKey")
    public int updateByPrimaryKey(THuman human) {
        return humanService.updateByPrimaryKey(human);
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/deleteByPrimaryKey")
    public int deleteByPrimaryKey(int id) {
        return humanService.deleteByPrimaryKey(id);
    }
}
