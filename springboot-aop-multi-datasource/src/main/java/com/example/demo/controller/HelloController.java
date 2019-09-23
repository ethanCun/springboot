package com.example.demo.controller;

import com.example.demo.config.TargetDataSource;
import com.example.demo.entity.test1.User1;
import com.example.demo.entity.test2.User2;
import com.example.demo.service.test1.User1Service;
import com.example.demo.service.test2.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用aop 配置mybatis多数据源加载数据大概流程:
 *
 * 1. 在application中定义多个数据源
 * 2. 定义一个注解@TargetDataSource, 标注在方法上用来表示操作哪个数据库的数据
 * 3. 在切面中拦截@TargetDataSource的dataSource, 在ThreadLocal中设置dataSource
 *      的key
 * 4. AbstractRoutingDataSource的拦截之后会调用determineCurrentLookupKey()
 *      方法
 * 5. 在DataSourceConfig里面的sqlSessionFactory方法里面根据@Qualifier("dynamicDataSource")
 *      DataSource dataSource动态操作数据源
 * */

@RestController
public class HelloController {

    @Autowired
    private User1Service user1Service;

    @Autowired
    private User2Service user2Service;

    //指定加载数据库1
    @TargetDataSource(dataSource = "oneDataSource")
    @RequestMapping(value = "/saveToDb1", method = RequestMethod.POST)
    public int saveToDb1(@RequestBody User1 user1){

        return user1Service.saveToDb1(user1);
    }

    //指定加载数据库2
    @TargetDataSource(dataSource = "twoDataSource")
    @RequestMapping(value = "/saveToDb2", method = RequestMethod.POST)
    public int saveToDb2(@RequestBody User2 user2){

        return user2Service.saveToDb2(user2);
    }
}
