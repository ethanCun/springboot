package com.example.demo.service.imp;

import com.example.demo.dao.GameDao;
import com.example.demo.domain.Game;
import com.example.demo.service.GameService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("gameService")
public class GameServiceImp implements GameService {

    @Autowired
    private GameDao gameDao;

    @Override
    public Map<String, Object> findAll(int page, int rows) {

        PageHelper.startPage(page, rows);

        //mybatis的第一条查询语句应该紧跟startPage 这样才会被分页
        List<Game> games = gameDao.findAll();

        PageInfo<Game> gamePageInfo = new PageInfo<>(games);

        Map<String, Object> map = new HashMap<>();

        map.put("total", gamePageInfo.getTotal());
        map.put("rows", gamePageInfo.getList());

        PageHelper.clearPage();

        return map;
    }
}
