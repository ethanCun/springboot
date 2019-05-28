package com.example.demo.service;


import com.example.demo.domain.UserInfo;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(String uid);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(String uid);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}
