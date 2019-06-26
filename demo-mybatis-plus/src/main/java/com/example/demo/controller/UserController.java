package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Game;
import com.example.demo.mapper.GameMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Resource
    private GameMapper gameMapper;

    @GetMapping(value = "/getByPage")
    public JSONObject getByPage(int index, int limit){

        QueryWrapper<Game> gameQueryWrapper = new QueryWrapper<>();

        IPage<Game> gameIPage = gameMapper.selectPage(new Page<>(index, limit), gameQueryWrapper);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", JSON.toJSON(gameIPage));

        return jsonObject;
    }

    @GetMapping(value = "/selectOne")
    public JSONObject selectOne(){

        QueryWrapper<Game> gameQueryWrapper = new QueryWrapper<>();
        gameQueryWrapper.lambda().eq(Game::getId, 1);

        Game game = gameMapper.selectOne(gameQueryWrapper);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", JSON.toJSON(game));

        return jsonObject;
    }

    @GetMapping(value = "/update")
    public int updateGame(Game game){

        return gameMapper.updateById(game);
    }

    @GetMapping(value = "/deleteById")
    public int deleteGameById(int id){

        return gameMapper.deleteByPrimaryKey(id);
    }

    @GetMapping(value = "/addGame")
    public int addGame(Game game){

        return gameMapper.insert(game);
    }
}
