package com.example.demo.service;

import com.example.demo.domain.UserInfo;

import java.util.List;

public interface UserService {

    List<UserInfo> getAllUserInfos();

    UserInfo getUserInfoWithId(int id);

    int deleteUserInfoWithId(int id);
}
