package com.example.demo.controller;

import com.example.demo.Result.ServerResult;
import com.example.demo.entity.User;
import com.example.demo.service.UserInfoService;
import javafx.geometry.Pos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value = "/allUsers")
    public ServerResult getAllUsers(){

        List<User> users = userInfoService.getAllUsers();

        if (users != null){

            return ServerResult.success(users);
        }else {

            return ServerResult.failure(users);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getUserWithId")
    public ServerResult<User> getUserWithId(int id){

        User user = userInfoService.getUserWithId(id);

        if (user != null){

            return ServerResult.success(user);
        }else {

            return ServerResult.failure(user);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ServerResult insertUser(User user){

        int res = userInfoService.insertUser(user);

        if (res > 0) {

            return ServerResult.success(res);
        }else {

            return ServerResult.failure(res);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUserWithId", method = RequestMethod.POST)
    public ServerResult deleteUserWithId(int id){

        int res = userInfoService.deleteUserWithId(id);

        if (res > 0){

            return ServerResult.success(res);
        }else {

            return ServerResult.failure(res);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ServerResult updateUser(User user){

        int res = userInfoService.updateUser(user);

        if (res > 0){

            return ServerResult.success(res);
        }else {

            return ServerResult.failure(res);
        }
    }
}
