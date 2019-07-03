package com.example.demo.Service;

import com.example.demo.entity.User;

public interface UserService {

    User findUserWithId(String id);

    User findUserWithUserName(String username);

    Integer addUser(User user);

    int updateToken(Integer id, String token);

    //测试在service层抛出异常 全局捕捉
    void testThrowExceptionAtServiceLayer();
}
