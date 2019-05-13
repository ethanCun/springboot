package com.example.demo.service.imp;

import com.example.demo.dao.GameDao;
import com.example.demo.domain.Game;
import com.example.demo.domain.Page;
import com.example.demo.service.GameService;
import com.example.demo.utils.NameUtils;
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
    public Map<String, Object> findAll(Page page, Game game) {

        //设置分页并根据sort和sortOrder排序
        if (page.getSort() == null || page.getSort().equals("")){

            PageHelper.startPage(page.getPage(), page.getRows());
        }else {

            //NameUtils.humpToLine2(page.getSort()): 驼峰转下划线
            PageHelper.startPage(page.getPage(), page.getRows(), NameUtils.humpToLine2(page.getSort()) + " " + page.getSortOrder());
        }

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
