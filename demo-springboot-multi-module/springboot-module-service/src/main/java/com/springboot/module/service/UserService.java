package com.springboot.module.service;

import com.springboot.module.entity.User;
import com.springboot.module.models.UserModel;

import java.util.List;

public interface UserService {

    List<User> userList();
}
