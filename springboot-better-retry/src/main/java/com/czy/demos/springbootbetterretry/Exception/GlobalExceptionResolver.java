package com.czy.demos.springbootbetterretry.Exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * 配置全局异常捕捉类：
 *
 * 注意：
 * 1. 可以捕捉到controller和service层中的异常
 * 2. 如果在controller或者service或者在aop中使用了try catch捕捉了异常 则不会再在这里进行处理
 */

//spring-boot-starter-aop
@ControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(value = CzyException.class)
    public @ResponseBody JSONObject czyExceptionHandler(CzyException e){

        System.out.println(".....czyExceptionHandler");

        //fastjson
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", null);
        jsonObject.put("message", e.getMessage());
        jsonObject.put("code", -1);

        return jsonObject;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(){

        System.out.println(".....exceptionHandler");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("czyerror");

        return modelAndView;
    }
}
