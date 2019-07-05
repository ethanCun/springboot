package com.example.demo.common.ExceptionHandler;


public class CustomException extends RuntimeException {

    public String message;
    public Integer code;

    public CustomException(CustomExceptionEnum customExceptionEnum){

        this.message = customExceptionEnum.message;
        this.code = customExceptionEnum.code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
