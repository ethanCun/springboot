package com.example.demo.service.test1.imp;

import com.example.demo.entity.test1.User1;
import com.example.demo.mapper.test1.User1Dao;
import com.example.demo.service.test1.User1Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("user1Service")
public class User1ServiceImp implements User1Service {

    @Resource
    private User1Dao user1Dao;

    @Override
    public int saveToDb1(User1 user1) {
        return user1Dao.saveToDb1(user1);
    }
}
