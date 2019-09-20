package com.example.demo.service.test1.imp;

import com.example.demo.entity.test1.User1;
import com.example.demo.service.test1.test1Service;
import com.example.demo.dao.test1.test1Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("test1Service")
public class test1ServiceImp implements test1Service{

    @Resource
    private test1Dao test1Dao;

    @Override
    public int save(User1 user1) {
        System.out.println("---user1 = " + user1);

        return test1Dao.save(user1);
    }
}
