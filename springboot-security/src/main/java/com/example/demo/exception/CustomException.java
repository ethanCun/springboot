package com.example.demo.exception;

public class CustomException extends RuntimeException {

    private Integer code;

    public CustomException(HttpStatusEnum httpStatusEnum){

        super(httpStatusEnum.getMessage());

        code = httpStatusEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
