package com.example.demo.service.imp;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserInfoService")
public class UserInfoServiceImp implements UserInfoService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {

        List<User> users = userDao.getAllUsers();

        return userDao.getAllUsers();
    }

    @Override
    public User getUserWithId(int id) {
        return userDao.getUserWithId(id);
    }

    @Override
    public int deleteUserWithId(int id) {
        return userDao.deleteUserWithId(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}
