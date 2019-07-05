package com.example.demo.app.service.imp;

import com.alibaba.fastjson.JSON;
import com.example.demo.app.dao.UserMapper;
import com.example.demo.app.entity.User;
import com.example.demo.app.service.UserService;
import com.example.demo.common.Base.BaseModel;
import com.example.demo.common.utils.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service("userService")
public class UserServiceImp implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImp.class.toString());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserWithUsername(String username) {
        return userMapper.getUserWithUsername(username);
    }

    @Override
    public BaseModel loginWithUser(User user) {

        User user1 = this.userMapper.getUserWithUsername(user.getUsername());

        //验证密码是否存在
        if (user1 == null){

            return BaseModel.fail(null, "账号不能为空", BaseModel.failCode);
        }

        //验证密码是否准确
        if (!(user.getPassword().equals(user1.getPassword()))){

            return BaseModel.fail(null, "密码错误", BaseModel.failCode);
        }

        //用户登录成功
        //获取由用户信息和当前时间生成的token
        String token = TokenHelper.generateToken(new TokenHelper(user1, System.currentTimeMillis()));

        //将token存入redis中，key：user1转换为json字符串 value：token
        this.stringRedisTemplate.opsForValue().set(JSON.toJSONString(user1), token, 30, TimeUnit.SECONDS);

        logger.info("缓存到redis成功 userid = " + user1.getId().toString() + "token = " + token);

        user1.setToken(token);

        return BaseModel.success(user1, BaseModel.successMsg, BaseModel.successCode);
    }
}
