package com.example.demo.service.UserServiceImp;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.Page;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findUserList(HttpServletRequest request, Map<String, String> param) {

        Integer page = Integer.parseInt(param.get("page"));
        Integer limit = Integer.parseInt(param.get("limit"));

        PageHelper.startPage(page, limit, true);

        List<User> users = userMapper.selectAll();

        PageInfo<User> userPageInfo = new PageInfo<>(users);

        return userPageInfo;
    }

    @Override
    public PageInfo<User> findUserList(HttpServletRequest request, Page page) {

        PageHelper.startPage(page.getPage(), page.getLimit(), true);

        List<User> users = userMapper.selectAll();

        PageInfo<User> pageInfo = new PageInfo<>(users);

        return pageInfo;
    }
}
