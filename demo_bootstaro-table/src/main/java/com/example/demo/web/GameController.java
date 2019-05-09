package com.example.demo.web;

import com.example.demo.domain.Game;
import com.example.demo.domain.Page;
import com.example.demo.service.GameService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @ResponseBody
    @RequestMapping(value = "/findAllGames", method = RequestMethod.GET)
    public Map<String, Object> findAllGames(Page page, Game game){

        Map<String, Object> map = gameService.findAll(page.getPage(), page.getRows());

        System.out.println("map = " + map);

        return map;
    }

    @RequestMapping(value = "/allGamesHome", method = RequestMethod.GET)
    public String allGamesHome(){

        return "home";
    }
}
