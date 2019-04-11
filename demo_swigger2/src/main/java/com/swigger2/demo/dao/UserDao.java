package com.swigger2.demo.dao;

import com.swigger2.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> fetchAllUser();

    User getUserWithId(int id);

    int insertUser(User user);

    int deleteUserWithId(int id);

    int updateUser(User user);
}
