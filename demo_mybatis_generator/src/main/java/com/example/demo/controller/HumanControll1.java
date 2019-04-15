package com.example.demo.controller;

import com.example.demo.entity.Human;
import com.example.demo.service.HumanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "mybatis-mapper", description = "human相关api")
@RestController
@RequestMapping(value = "/test1")
public class HumanControll1 {

    @Autowired
    private HumanService humanService;

    @ApiOperation(value = "查询", notes = "所有信息")
    @GetMapping(value = "/allHumans")
    public List<Human> allHumans(){

        return humanService.allHumans();
    }

    @ApiOperation(value = "查询", notes = "根据id查询")
    @GetMapping(value = "/getHumanById")
    public Human getHumanById(int id){

        return humanService.getHumanById(id);
    }

    @ApiOperation(value = "插入")
    @PostMapping(value = "/insertHuman")
    public int insertHuman(Human human){

        return humanService.insertHuman(human);
    }

    @ApiOperation(value = "更新")
    @PostMapping(value = "/updateHuman")
    public int updateHuman(Human human) {
        return humanService.updateHuman(human);
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/deleteHumanById")
    public int deleteHumanById(int id) {
        return humanService.deleteHumanById(id);
    }
}
