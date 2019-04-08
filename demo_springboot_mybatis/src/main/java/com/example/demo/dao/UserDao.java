package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> getAllUsers();

    User getUserWithId(int id);

    int insertUser(User user);

    int deleteUserWithId(int id);

    int updateUser(User user);
}
