package com.springboot.demo.dao;

import com.springboot.demo.entity.User;

import java.util.List;

//由于jpa会自动解析方法名，因此像通过id查找对象只需写明findById，表示通过id去检索数据，其他字段一样。
public interface UserDao {

    User findById(int id);

    int save(User user);

    void deleteUserById(int id);

    List<User> findAll();
}
