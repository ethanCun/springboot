package com.example.demo.service.imp;

import com.example.demo.dao.HumanDao;
import com.example.demo.dao.THumanMapper;
import com.example.demo.entity.Human;
import com.example.demo.entity.THuman;
import com.example.demo.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("humanService")
public class HumanServiceImp implements HumanService {

    @Resource
    private HumanDao humanDao;

    @Resource
    private THumanMapper tHumanMapper;

    @Override
    public List<Human> allHumans() {

        return humanDao.allHumans();
    }

    @Override
    public Human getHumanById(int id) {
        return humanDao.getHumanById(id);
    }

    @Override
    public int insertHuman(Human human) {
        return humanDao.insertHuman(human);
    }

    @Override
    public int updateHuman(Human human) {
        return humanDao.updateHuman(human);
    }

    @Override
    public int deleteHumanById(int id) {
        return humanDao.deleteHumanById(id);
    }


    @Override
    public List<THuman> selectAll() {
        return tHumanMapper.selectAll();
    }

    @Override
    public THuman selectByPrimaryKey(int id) {
        return tHumanMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(THuman human) {
        return tHumanMapper.insert(human);
    }

    @Override
    public int updateByPrimaryKey(THuman human) {
        return tHumanMapper.updateByPrimaryKey(human);
    }

    @Override
    public int deleteByPrimaryKey(int id) {
        return tHumanMapper.deleteByPrimaryKey(id);
    }
}
