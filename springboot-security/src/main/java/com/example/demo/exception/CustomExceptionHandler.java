package com.example.demo.exception;

import com.example.demo.model.BaseModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

    //自定义异常处理
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public BaseModel errorHandleJson(CustomException exp){

        System.out.println("exp = " + exp);

        return BaseModel.fail( exp.getCode(), exp.getMessage(), null);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseModel errorHandler(Exception exp){

        System.out.println("exp = " + exp);

        return BaseModel.fail(-1, exp.getMessage(), null);
    }
}
