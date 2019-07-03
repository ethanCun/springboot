package com.example.demo.Service.ServiceImp;

import com.example.demo.Service.UserService;
import com.example.demo.common.handleException.CustomException.CustomException;
import com.example.demo.common.handleException.CustomException.HttpStatusEnum;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserWithId(String id) {
        return userMapper.findUserWithId(id);
    }

    @Override
    public User findUserWithUserName(String username) {
        return userMapper.findUserWithUserName(username);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateToken(Integer id, String token) {
        return userMapper.updateToken(id, token);
    }

    @Override
    public void testThrowExceptionAtServiceLayer() {

        throw new CustomException(HttpStatusEnum.UserIsNotExist);
    }
}
