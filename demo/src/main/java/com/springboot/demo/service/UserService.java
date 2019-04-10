package com.springboot.demo.service;

import com.springboot.demo.entity.User;

import java.util.List;

public interface UserService {

    int save(User user);

    User findById(int id);

    void deleteUserById(int id);

    List<User> findAll();
}
