package com.example.demo.service.imp;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getAllUserInfos() {

        return userInfoMapper.selectAll();
    }

    @Override
    public UserInfo getUserInfoWithId(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteUserInfoWithId(int id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }
}
