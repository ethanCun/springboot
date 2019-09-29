package com.example.demo.service.test2.imp;

import com.example.demo.dao.test2.test2Dao;
import com.example.demo.entity.test2.User2;
import com.example.demo.service.test2.test2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("test2Service")
public class test2ServiceImp implements test2Service {

    @Resource
    private test2Dao test2Dao;

    @Override
    public int save(User2 user2) {
        return test2Dao.save(user2);
    }
}
