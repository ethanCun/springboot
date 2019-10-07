package com.example.demo.service;


import com.example.demo.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

//继承于UserDetailsService
public interface UserService extends UserDetailsService {

    int addUser(SysUser user);
}
