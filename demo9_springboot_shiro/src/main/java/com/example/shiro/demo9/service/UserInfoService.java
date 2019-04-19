package com.example.shiro.demo9.service;

import com.example.shiro.demo9.model.UserInfo;

public interface UserInfoService {

     /*
      * 通过用户名查询用户
      *
      * @author ethan
      * @date 2019/4/3 6:49 PM
      * @param
      * @return
      */
    public UserInfo findByUserName(String username);
}
