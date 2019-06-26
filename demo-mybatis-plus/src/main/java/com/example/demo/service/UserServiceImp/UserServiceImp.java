package com.example.demo.service.UserServiceImp;

import com.example.demo.entity.Game;
import com.example.demo.mapper.GameMapper;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userService")
public class UserServiceImp implements UserService {

    @Resource
    private GameMapper gameMapper;

    @Override
    public List<Game> allUsers() {

        return gameMapper.selectAll();
    }

}
