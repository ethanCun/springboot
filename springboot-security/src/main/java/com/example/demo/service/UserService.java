package com.example.demo.service;


import com.example.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

//继承于UserDetailsService
public interface UserService extends UserDetailsService {

    int addUser(User user);
}
