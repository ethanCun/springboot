#### application.yml:
```
mybatis:
  type-aliases-package: com.example.demo.entity
  mapper-locations: classpath:mapper/*.xml
  configuration:
    map-underscore-to-camel-case: true #Spring boot下，mybatis配置驼峰命名法和下划线风格转换


spring:
  datasource:
      url: jdbc:mysql://localhost:3306/test
      username: root
      password: czy1212
      driver-class-name: com.mysql.cj.jdbc.Driver
      type: com.alibaba.druid.pool.DruidDataSource
```
#### controller
```
package com.example.demo.controller;

import com.example.demo.Result.ServerResult;
import com.example.demo.entity.User;
import com.example.demo.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value = "/allUsers")
    public ServerResult getAllUsers(){

        List<User> users = userInfoService.getAllUsers();

        if (users != null){

            return ServerResult.success(users);
        }else {

            return ServerResult.failure(users);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getUserWithId")
    public ServerResult<User> getUserWithId(int id){

        User user = userInfoService.getUserWithId(id);

        if (user != null){

            return ServerResult.success(user);
        }else {

            return ServerResult.failure(user);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ServerResult insertUser(User user){

        int res = userInfoService.insertUser(user);

        if (res > 0) {

            return ServerResult.success(res);
        }else {

            return ServerResult.failure(res);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUserWithId", method = RequestMethod.POST)
    public ServerResult deleteUserWithId(int id){

        int res = userInfoService.deleteUserWithId(id);

        if (res > 0){

            return ServerResult.success(res);
        }else {

            return ServerResult.failure(res);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ServerResult updateUser(User user){

        int res = userInfoService.updateUser(user);

        if (res > 0){

            return ServerResult.success(res);
        }else {

            return ServerResult.failure(res);
        }
    }
}
```
#### dao
```
package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> getAllUsers();

    User getUserWithId(int id);

    int insertUser(User user);

    int deleteUserWithId(int id);

    int updateUser(User user);
}

```
#### entity
```
package com.example.demo.entity;

public class User {

    private int id;
    private String userName;
    private String nickName;
    private String passWord;
    private String userSex;

    public User() {
    }

    public User(int id, String userName, String nickName, String passWord, String userSex) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.passWord = passWord;
        this.userSex = userSex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
```
#### Result
```
package com.example.demo.Result;

import java.io.Serializable;

public class ServerResult<T> implements Serializable {

    private static final long serialVersionUID = 7393154872520762719L;

    private T data;
    private String message;
    private int code;

    public ServerResult() {
    }

    public ServerResult(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    //自定义返回结果
    public static <T> ServerResult<T> result(T data, String message, int code){
        return new ServerResult<>(data, message, code);
    }

    //成功
    public static <T> ServerResult<T> success(T data){
        return new ServerResult<>(data, "success", 0);
    }

    //失败
    public static <T> ServerResult<T> failure(T data){
        return new ServerResult<>(data, "failure", -1);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
```
#### service
```
package com.example.demo.service;


import com.example.demo.entity.User;

import java.util.List;

public interface UserInfoService {

    List<User> getAllUsers();

    User getUserWithId(int id);

    int insertUser(User user);

    int deleteUserWithId(int id);

    int updateUser(User user);
}
```
#### service.imp
```
package com.example.demo.service.imp;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserInfoService")
public class UserInfoServiceImp implements UserInfoService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {

        List<User> users = userDao.getAllUsers();

        return userDao.getAllUsers();
    }

    @Override
    public User getUserWithId(int id) {
        return userDao.getUserWithId(id);
    }

    @Override
    public int deleteUserWithId(int id) {
        return userDao.deleteUserWithId(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}
```
