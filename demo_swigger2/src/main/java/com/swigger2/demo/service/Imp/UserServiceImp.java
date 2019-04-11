package com.swigger2.demo.service.Imp;

import com.swigger2.demo.dao.UserDao;
import com.swigger2.demo.entity.User;
import com.swigger2.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> fetchAllUser() {
        return userDao.fetchAllUser();
    }

    @Override
    public User getUserWithId(int id) {
        return userDao.getUserWithId(id);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int deleteUserWithId(int id) {
        return userDao.deleteUserWithId(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
