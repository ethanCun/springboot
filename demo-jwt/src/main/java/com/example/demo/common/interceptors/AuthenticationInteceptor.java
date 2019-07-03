package com.example.demo.common.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.Service.UserService;
import com.example.demo.common.annotations.PassToken;
import com.example.demo.common.annotations.UserLoginToken;
import com.example.demo.common.handleException.CustomException.CustomException;
import com.example.demo.common.handleException.CustomException.HttpStatusEnum;
import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.logging.Logger;


/**
 * HandlerInterceptor接口主要定义了三个方法
 * 1.boolean preHandle ()：
 * 预处理回调方法,实现处理器的预处理，第三个参数为响应的处理器,自定义Controller,
 * 返回值为true表示继续流程（如调用下一个拦截器或处理器）或者接着执行postHandle()和afterCompletion()；
 * false表示流程中断，不会继续调用其他的拦截器或处理器，中断执行。
 * <p>
 * 2.void postHandle()：
 * 后处理回调方法，实现处理器的后处理（DispatcherServlet进行视图返回渲染之前进行调用），
 * 此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
 * <p>
 * 3.void afterCompletion():
 * 整个请求处理完毕回调方法,该方法也是需要当前对应的Interceptor的preHandle()的返回值为true时才会执行，
 * 也就是在DispatcherServlet渲染了对应的视图之后执行。用于进行资源清理。
 * 整个请求处理完毕回调方法。如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，
 * 类似于try-catch-finally中的finally，但仅调用处理器执行链中
 * <p>
 * 主要流程:
 * 1.从 http 请求头中取出 token，
 * 2.判断是否映射到方法
 * 3.检查是否有passtoken注释，有则跳过认证
 * 4.检查有没有需要用户登录的注解，有则需要取出并验证
 * 5.认证通过则可以访问，不通过会报相关错误信息
 */

//写一个拦截器去获取token并验证token
public class AuthenticationInteceptor implements HandlerInterceptor {

    private static Logger logger = Logger.getLogger(UserController.class.toString());

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //从请求头里获取前端传过来的token
        String token = request.getHeader("token");
        String requestURI = request.getRequestURI(); //返回请求行中的资源名称
        String requestUrl = request.getRequestURI().toString(); //获得客户端发送请求的完整url
        String paras = request.getQueryString(); //返回请求行中的参数部分
        String reuestAddress = request.getRemoteAddr(); //请求客户端的IP地址
        String host = request.getRemoteHost(); //返回发出请求的客户机的主机名
        Integer requestPort = request.getRemotePort(); //返回发出请求的客户机的端口号 61267。

        //将相关信息写入日志
        logger.info("requestURI:" + requestURI + " reuestAddress:"
                + reuestAddress + " requestPort:" + requestPort + " token:" + token);

        //不是映射到方法直接跳过
        if (!(handler instanceof HandlerMethod)) {

            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //检查是否有passtoken注释, 有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {

            PassToken passToken = method.getAnnotation(PassToken.class);

            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户认证token的权限， 有则需要验证，没有则直接跳过
        if (method.isAnnotationPresent(UserLoginToken.class)) {

            UserLoginToken userLoginToken = (UserLoginToken) method.getAnnotation(UserLoginToken.class);

            //如果需要验证
            if (userLoginToken.required()) {

                if (token == null) {
                    throw new RuntimeException("无token,请重新登录");
                }

                //获取本次token中的用户id
                String id;
                try {
                    id = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e) {
                    throw new CustomException(HttpStatusEnum.TokenIsNull);
                }

                //根据用户id从数据库获取用户信息
                User user = this.userService.findUserWithId(id);

                if (user == null) {
                    throw new CustomException(HttpStatusEnum.UserIsNotExist);
                }

                //验证本次token是有有效 有效期：测试1分钟
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

                try {

                    jwtVerifier.verify(token);

                    //判断本次请求传过来的token是否和数据库中的一致 如果不一致代表在别的设备上登录过了
                    if (!(user.getToken().equals(token))) {
                        throw new CustomException(HttpStatusEnum.TokenRequiredRelogin);
                    }

                } catch (JWTVerificationException e) {
                    throw new CustomException(HttpStatusEnum.TokenIsNotValid);
                }

                return true;
            }
        }

        //其他情况都不验证token
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }


}
