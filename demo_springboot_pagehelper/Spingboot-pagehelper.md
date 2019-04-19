#### pom.xml
```
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--devtools-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.15</version>
            <scope>runtime</scope>
        </dependency>

        <!--druid-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.0</version>
        </dependency>

        <!--简化java pojo-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.6</version>
        </dependency>

        <!--Swagger2-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>

        <!--pageHelper-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.10</version>
        </dependency>

    </dependencies>
```

#### applicatiom.properties
```

spring.datasource.url=jdbc:mysql://localhost:3306/app
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=czy1212
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource

spring.devtools.restart.enabled=true
spring.devtools.restart.additional-paths=src/main/java

spring.thymeleaf.cache=false

mybatis.type-aliases-package=com.example.demo.entity
mybatis.mapper-locations=classpath:mapper/*.xml
```
#### dao
```
package com.example.demo.dao;

import com.example.demo.entity.App;

import java.util.List;

public interface AppDao {

    List<App> getAppList();
}
```
#### mapper
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.demo.dao.AppDao">

    <!--
        都表示从1开始后面100条
        select * from `t_app` limit 100 offset 1
-->
    <select id="getAppList" resultType="App">

        select  * from `t_app`
    </select>
</mapper>
```
#### service & imp
```
package com.example.demo.service;

import com.example.demo.entity.App;
import com.github.pagehelper.PageInfo;

public interface AppService {

    PageInfo<App> getAppList(int pageNo, int pageSize);

}
```
```
package com.example.demo.service.imp;

import com.example.demo.dao.AppDao;
import com.example.demo.entity.App;
import com.example.demo.service.AppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appService")
public class AppServiceImp implements AppService {

    @Resource
    private AppDao appDao;

    @Override
    public PageInfo<App> getAppList(int pageNo, int pageSize) {

        //注意：只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
        //pageHelper
        PageHelper.startPage(pageNo, pageSize);

        //所以这个方法一定要紧跟着startPage 不仍然不会被分页
        List<App> apps = appDao.getAppList();

        //使用pageInfo包装
        PageInfo<App> appPageInfo = new PageInfo<>(apps);

        return appPageInfo;
    }
}
```
#### entity
```
package com.example.demo.entity;

import lombok.Data;

@Data
public class App {

    private int id;
    private String app_name;
    private int app_id;
    private int app_status;
}
```
#### controller
```
package com.example.demo.controller;

import com.example.demo.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppHomeController {

    @Autowired
    private AppService appService;

    @GetMapping(value = "/home")
    public String appHome(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                          Model model){

        model.addAttribute("apps", appService.getAppList(pageNo, pageSize));

        System.out.println(appService.getAppList(pageNo, pageSize));

        return "AppHome";
    }

    @ResponseBody
    @GetMapping(value = "/home2")
    public String appHome2(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                          Model model){

        model.addAttribute("apps", appService.getAppList(pageNo, pageSize));

        System.out.println(appService.getAppList(pageNo, pageSize));

        return "AppHome";
    }
}
```
#### app
```
package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }
}
```
#### AppHome.html
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>App列表</title>
</head>
<body>

<div style="float:left; width: 80%; height: 800px">

    <div class="container" style="margin: 0 auto; width: 1000px; border: black 1px solid">

        <h3 style="width:400px; text-align: center">我的应用</h3>

        <hr>

        <table class="tablelist">

            <thead>
                <tr th:align="left" class="d">
                    <th width="5%">序号</th>
                    <th width="10%">应用名称</th>
                    <th width="15%">AppID</th>
                    <th width="10%">状态</th>
                    <th width="10%">操作</th>
                </tr>
            </thead>

            <tbody>
                <tr th:each="app : ${apps.list}">
                    <td th:text="${app.id}"></td>
                    <td th:text="${app.app_name}"></td>
                    <td th:text="${app.app_id}"></td>
                    <td th:text="${app.app_status}"></td>
                    <td>
                        <a th:text="详情" href="#" onclick=""></a>
                    </td>
                </tr>
            </tbody>
        </table>

        <table class="gridtable" style="width:100%; text-align: center">

            <tr>
                <td width="10%" th:if="${apps.hasPreviousPage}">
                    <a th:href="'?pageNo=1&pageSize=5'">首页</a>
                </td>

                <td width="10%" th:if="${apps.hasPreviousPage}">
                    <a th:href="'?pageNo='+${apps.prePage}+'&pageSize=5'">上一页</a>
                </td>

                <td width="10%" th:each="nav : ${apps.navigatepageNums}">

                    <a th:href="'?pageNo='+${nav}+'&pageSize=5'" th:text="${nav}" th:if="${nav != apps.pageNum}"></a>
                    <span style="font-weight: bold" th:if="${nav == apps.pageNum}" th:text="${nav}"></span>
                </td>

                <td width="10%" th:if="${apps.hasNextPage}">
                    <a th:href="'?pageNo='+${apps.nextPage}+'&pageSize=5'">下一页</a>
                </td>

                <td width="10%" th:if="${apps.hasNextPage}">
                    <a th:href="'?pageNo='+${apps.pages}+'&pageSize=5'">尾页</a>
                </td>
            </tr>

        </table>

        <hr>

        <div>当前页号：<span th:text="${apps.pageNum}"></span></div>
        <div>每页条数：<span th:text="${apps.pageSize}"></span></div>
        <div>起始行号：<span th:text="${apps.startRow}"></span></div>
        <div>终止行号：<span th:text="${apps.endRow}"></span></div>
        <div>总结果数：<span th:text="${apps.total}"></span></div>
        <div>总页数:<span th:text="${apps.pages}"></span></div>

        <hr>

        <div>是否为第一页：<span th:text="${apps.isFirstPage}"></span> </div>
        <div>是否为最后一页：<span th:text="${apps.isLastPage}"></span></div>
        <div>是否有前一页：<span th:text="${apps.hasPreviousPage}"></span></div>
        <div>是否有后一页：<span th:text="${apps.hasNextPage}"></span></div>

    </div>
</div>

</body>
</html>
```
