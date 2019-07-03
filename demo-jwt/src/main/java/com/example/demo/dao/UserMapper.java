package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findUserWithId(String id);

    User findUserWithUserName(String username);

    Integer addUser(User user);

    int updateToken(@Param("id") Integer id, @Param("token") String token);
}
