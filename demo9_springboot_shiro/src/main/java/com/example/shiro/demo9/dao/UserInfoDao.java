package com.example.shiro.demo9.dao;

import com.example.shiro.demo9.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoDao extends CrudRepository<UserInfo, Long> {

    //通过用户名称查询用户信息
    public UserInfo findByUsername(String userName);
}
