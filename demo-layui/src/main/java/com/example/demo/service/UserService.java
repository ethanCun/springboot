package com.example.demo.service;

import com.example.demo.domain.Page;
import com.example.demo.domain.User;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {

    PageInfo<User> findUserList(HttpServletRequest request, @RequestParam Map<String, String> param);

    PageInfo<User> findUserList(HttpServletRequest request, @RequestBody Page page);

}
