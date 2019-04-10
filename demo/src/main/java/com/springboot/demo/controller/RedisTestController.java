package com.springboot.demo.controller;

import com.springboot.demo.base.utils.FastJson2JsonRedisSerializer;
import com.springboot.demo.base.utils.RedisConstants;
import com.springboot.demo.base.utils.RedisUtil;
import com.springboot.demo.base.controller.BaseController;
import com.springboot.demo.base.utils.StateParameter;
import com.springboot.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/redis")
public class RedisTestController extends BaseController {

    @Autowired
    RedisUtil redisUtil;

    private FastJson2JsonRedisSerializer<User> fastJson2JsonRedisSerializer
            = new FastJson2JsonRedisSerializer<>(User.class);

    @RequestMapping(value = "/test")
    @ResponseBody
    public ModelMap test(){

        try {

            redisUtil.set("redisTemplate", "是一条测试数据", RedisConstants.datebase3);
            String value = redisUtil.get("redisTemplate", RedisConstants.datebase2).toString();
            logger.info("redisTemplate = " + value);

            return getModelMap(StateParameter.SUCCESS, "redisTemplate="+value, "测试成功");

        }catch (Exception e){

            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "测试失败");
        }
    }

    @RequestMapping(value = "/testUser")
    @ResponseBody
    public ModelMap setUser(){

        try {

            User user = new User();
            user.setName("隔壁老王");
            user.setAge(30);

            redisUtil.set("user", user, RedisConstants.datebase1);

            User user1 = (User)redisUtil.get("user", RedisConstants.datebase1);

            logger.info("user1 = " + user1);

            return getModelMap(StateParameter.SUCCESS, user1, "成功");
        }catch (Exception e){

            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "失败");
        }
    }

    @RequestMapping(value = "/testSerialize")
    @ResponseBody
    public byte[] testSerialize(){

        User user = new User();
        user.setName("czy");
        user.setAge(22);

        System.out.println(fastJson2JsonRedisSerializer);
        System.out.println(fastJson2JsonRedisSerializer.serialize(user));

        return fastJson2JsonRedisSerializer.serialize(user);
    }

    @RequestMapping(value = "/testDeserialize")
    @ResponseBody
    public User testDeserialize(){

        String userStr = "{\"@type\":\"com.springboot.demo.entity.User\",\"age\":22,\"id\":0,\"name\":\"czy\"}";

        byte[] bytes = userStr.getBytes();

        return fastJson2JsonRedisSerializer.deserialize(bytes);
    }
}
