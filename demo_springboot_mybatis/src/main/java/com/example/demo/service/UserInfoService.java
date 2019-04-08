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
