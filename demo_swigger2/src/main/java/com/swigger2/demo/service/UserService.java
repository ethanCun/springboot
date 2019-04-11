package com.swigger2.demo.service;

import com.swigger2.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> fetchAllUser();

    User getUserWithId(int id);

    int insertUser(User user);

    int deleteUserWithId(int id);

    int updateUser(User user);
}
