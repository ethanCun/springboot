package com.springboot.module.service.imp;

import com.springboot.module.entity.User;
import com.springboot.module.mapper.UserMapper;
import com.springboot.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    @Override
    public List<User> userList() {

        return this.userMapper.selectAll();
    }
}
