package com.example.shiro.demo9.service.imp;

import com.example.shiro.demo9.dao.UserInfoDao;
import com.example.shiro.demo9.model.UserInfo;
import com.example.shiro.demo9.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImp implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUserName(String username) {

        UserInfo userInfo = userInfoDao.findByUsername(username);

        return userInfoDao.findByUsername(username);
    }
}
