package com.example.demo.common.Config;

import com.alibaba.fastjson.JSON;
import com.example.demo.app.service.UserService;
import com.example.demo.common.Annotation.RequireToken;
import com.example.demo.common.ExceptionHandler.CustomException;
import com.example.demo.common.ExceptionHandler.CustomExceptionEnum;
import com.example.demo.common.utils.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInteceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("token");

        //不是映射到方法直接跳过验证
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        //检查是否有注解
        if (method.isAnnotationPresent(RequireToken.class)){

            RequireToken requireToken = (RequireToken)method.getAnnotation(RequireToken.class);

            if (requireToken.require()){

                if (token == null || ("".equals(token))){
                    throw new CustomException(CustomExceptionEnum.TokenIsNull);
                }

                //根据本次token获取用户信息
                TokenHelper tokenHelper = TokenHelper.getUserWithToken(token);

                //根据时间判断是否过期 过期时间30秒
                if (Math.abs(System.currentTimeMillis()-tokenHelper.getTime()) > 30000){
                    throw new CustomException(CustomExceptionEnum.TokenIsInvalidate);
                }

                //根据user id查询redis缓存中是否存在一致的token
                String redisToken = this.stringRedisTemplate.opsForValue().get(JSON.toJSONString(tokenHelper.getUser()));

                if (redisToken == null){

                    throw new CustomException(CustomExceptionEnum.TokenIsInvalidate);
                }

                if (!(token.equals(redisToken))){
                    throw new CustomException(CustomExceptionEnum.AccountLoginOtherDervice);
                }

                return true;

            }

            return true;
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
