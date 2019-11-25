package com.example.demos.demoforappfront.service.imp;

import com.example.demos.demoforappfront.dao.UserMapper;
import com.example.demos.demoforappfront.entity.User;
import com.example.demos.demoforappfront.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryUserList() {
        return this.userMapper.selectAll();
    }

    @Override
    public List<User> queryUserListLike(String name) {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("username", "%"+name+"%");

        return this.userMapper.selectByExample(example);
    }
}
