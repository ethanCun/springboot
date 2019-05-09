#### web
```
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
```
#### service.imp
```
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
```
#### dao
```
package com.example.demo.dao;

import com.example.demo.domain.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GameDao {

    List<Game> findAll();
}

```
#### mapper
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.demo.dao.GameDao">

    <!--sql语句后面别带分号；-->
    <select id="findAll" resultType="Game">
        select * from `game`
    </select>
</mapper>
```
#### domain game
```
package com.example.demo.domain;


public class Game {

    private int id;
    private String cnName;
    private String jpName;
    private String enName;
    private String nature;
    private String generation;
    private String power;
    private String hirate;
    private String type;
    private String pp;

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", cnName='" + cnName + '\'' +
                ", jpName='" + jpName + '\'' +
                ", enName='" + enName + '\'' +
                ", nature='" + nature + '\'' +
                ", generation='" + generation + '\'' +
                ", power='" + power + '\'' +
                ", hirate='" + hirate + '\'' +
                ", type='" + type + '\'' +
                ", pp='" + pp + '\'' +
                '}';
    }

    public Game() {
    }

    public Game(int id, String cnName, String jpName, String enName, String nature, String generation, String power, String hirate, String type, String pp) {
        this.id = id;
        this.cnName = cnName;
        this.jpName = jpName;
        this.enName = enName;
        this.nature = nature;
        this.generation = generation;
        this.power = power;
        this.hirate = hirate;
        this.type = type;
        this.pp = pp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getJpName() {
        return jpName;
    }

    public void setJpName(String jpName) {
        this.jpName = jpName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getHirate() {
        return hirate;
    }

    public void setHirate(String hirate) {
        this.hirate = hirate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }
}
```
#### domain page
```
package com.example.demo.domain;

public class Page {

    private int rows;
    private int page;

    @Override
    public String toString() {
        return "Page{" +
                "rows=" + rows +
                ", page=" + page +
                '}';
    }

    public Page() {
    }

    public Page(int rows, int page) {
        this.rows = rows;
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}

```
#### app
```
package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@MapperScan(basePackages = "com.example.demo.dao")
public class DemoApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //这里配置静态资源文件的路径导包都是默认的直接导入就可以
    //配置spring启动的时候加载static下面的静态文件一起运行
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }
}
```
#### home.html
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>

    <!-- 解决glyphicons-halflings-regular 字体找不到 -->
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">

    <!--本地引入字体-->
    <!--<link rel="stylesheet" data-th-href="@{/static/font/glyphicons-halflings-regular.woff}">-->
    <!--<link rel="stylesheet" data-th-href="@{/static/font/glyphicons-halflings-regular.woff2}">-->

    <!--data-th-href 与 data-th-src 引入不了-->
    <link rel="stylesheet" data-th-href="@{/static/css/bootstrap.min.css}">
    <!--<script data-th-src="@{js/app/system/user/user.js}"></script>-->

    <!--<link rel="stylesheet" th:href="@{/static/css/bootstrap.min.css}">-->

    <script th:src="@{/static/js/jquery-3.3.1.min.js}"></script>

    <script th:src="@{/static/js/bootstrap.min.js}"></script>

    <link rel="stylesheet" th:href="@{/static/bootstrap-table-master/dist/bootstrap-table.min.css}">

    <script th:src="@{/static/bootstrap-table-master/dist/bootstrap-table.min.js}"></script>
    <script th:src="@{/static/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js}"></script>

    <!--自定义game.js-->
    <script th:src="@{/static/js/game/game.js}"></script>

    <meta charset="UTF-8">
    <title>信息列表</title>

</head>
<body>

<table id="gameList" class="table table-hover"></table>


</body>
</html>
```
#### game.js
```

$(function () {


    $("#gameList").bootstrapTable({

        url: '/game/findAllGames',
        method:'GET',
        toolbar: '#toolbar',
        striped:'true', //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        uniqueId: 'ID',
        pagination: true, //分页
        sortable: true,
        sortOrder: "asc", //排序方式 asc升序  desc降序
        sidePagination : 'server', //服务器端分页
        pageNumber:1,
        pageSize:5,
        pageList:[5, 10, 20, 25],
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到的参数:
        queryParams:function(params){

            console.log("params = " + JSON.stringify(params));

            //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
            var temp = {

                rows:params.limit, //页面大小
                page:(params.offset/params.limit) + 1,//页码
                sort:params.sort, //排序列名
                sortOrder:params.order //排位命令（desc，asc）
            };

            console.log("temp = " + JSON.stringify(temp));

            return temp;
        },
        columns:[
            {
                checkbox:true,
                align:'center'
            },
            {
                field:'id',
                title:'编号',
                sortable:true
            },
            {
                field:'cnName',
                title:'中文名',
                sortable:true
            },
            {
                field:'jpName',
                title:'日文名',
                sortable:true
            },
            {
                field:'enName',
                title:'英文名',
                sortable:true
            },
            {
                field:'nature',
                title:'属性',
                sortable:true
            },
            {
                field:'generation',
                title:'世代',
                sortable:true
            },
            {
                field:'power',
                title:'威力',
                sortable:true
            },
            {
                field:'hirate',
                title:'命中',
                sortable:true
            },
            {
                field:'type',
                title:'攻击类型',
                sortable:true
            },
            {
                field:'pp',
                title:'pp点数',
                sortable:true
            },
            {
                field:'pp',
                title:'操作',
                width:120,
                align:'center',
                valign:'middle',
                formatter:actionFormatter,
            }
        ],
        onLoadSuccess:function(data){

            console.log("success:data = " + JSON.stringify(data));
        },
        onLoadError:function(error){

            console.log("error: error = " + error);
        },
        onDblClickRow:function(row, value, index){

            console.log("onDbClickRow: row =" + JSON.stringify(row) + " value = " + JSON.stringify(value) + " index = " + index);
        }
    });

    //操作栏的格式化
    function actionFormatter(value, row, index) {

        var id = value;
        var result = "";

        result += "<a href='javascript:;' class='btn btn-xs green' onclick='' title='查看'>" +
            " <span class='glyphicon glyphicon-search'></span></a>";
        result += "<a href='javascript:;' class='btn btn-xs blue' onclick='' title='编辑'>" +
            "<span class='glyphicon glyphicon-pencil'></span></a>";
        result += "<a href='javascript:;' class='btn btn-xs red' onclick='' title='删除'>" +
            "<span class='glyphicon glyphicon-remove'></span></a>"

        return result;
    }
})

$(document).ready(function () {

    console.log('========');


});
```
