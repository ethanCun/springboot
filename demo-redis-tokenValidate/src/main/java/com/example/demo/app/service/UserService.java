package com.example.demo.app.service;

import com.example.demo.app.entity.User;
import com.example.demo.common.Base.BaseModel;

public interface UserService {

    User getUserWithUsername(String username);

    BaseModel loginWithUser(User user);
}
