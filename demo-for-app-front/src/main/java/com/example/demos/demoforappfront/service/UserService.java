package com.example.demos.demoforappfront.service;

import com.example.demos.demoforappfront.entity.User;

import java.util.List;

public interface UserService {

    List<User> queryUserList();

    List<User> queryUserListLike(String name);
}
