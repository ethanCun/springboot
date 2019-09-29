package com.example.demo.service.test2.imp;

import com.example.demo.entity.test2.User2;
import com.example.demo.mapper.test2.User2Dao;
import com.example.demo.service.test2.User2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("user2Service")
public class User2ServiceImp implements User2Service {

    @Resource
    private User2Dao user2Dao;

    @Override
    public int saveToDb2(User2 user2) {
        return user2Dao.saveToDb2(user2);
    }
}
