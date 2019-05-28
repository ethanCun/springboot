package com.example.demo.service.imp;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(String uid) {
        return userInfoMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(String uid) {
        return userInfoMapper.selectByPrimaryKey(uid);
    }

    @Override
    public List<UserInfo> selectAll() {
        return userInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }
}
