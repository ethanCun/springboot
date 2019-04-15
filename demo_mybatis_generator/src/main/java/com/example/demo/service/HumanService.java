package com.example.demo.service;

import com.example.demo.entity.Human;
import com.example.demo.entity.THuman;

import java.util.List;

public interface HumanService {

    List<Human> allHumans();

    Human getHumanById(int id);

    int insertHuman(Human human);

    int updateHuman(Human human);

    int deleteHumanById(int id);

    //mybatis-generator
    List<THuman> selectAll();

    THuman selectByPrimaryKey(int id);

    int insert(THuman human);

    int updateByPrimaryKey(THuman human);

    int deleteByPrimaryKey(int id);
}
