package com.example.demo.common.handleException;

import com.example.demo.common.BaseModel;
import com.example.demo.common.handleException.CustomException.CustomException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//全局异常统一处理
@ControllerAdvice
public class CustomExceptionHandle {

    //自定义异常处理
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public BaseModel errorHandleJson(CustomException exp){

        return BaseModel.fail(null, exp.getMessage(), exp.getCode());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseModel errorHandler(Exception exp){

        return BaseModel.fail(null, exp.getMessage(), -1);
    }
}
