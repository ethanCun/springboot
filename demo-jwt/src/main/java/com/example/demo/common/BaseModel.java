package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BaseModel {

    private static final int SUCCESS_CODE = 0;
    private static final int FAIL_CODE=-1;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private Object data;
    private String message;
    private Integer code;

    public BaseModel(){}

    public BaseModel(Object data, String message, Integer code){
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static BaseModel success(Object data){

        return new BaseModel(data, SUCCESS, SUCCESS_CODE);
    }

    public static BaseModel success(Object data, String message){

        return new BaseModel(data, message, SUCCESS_CODE);
    }

    public static BaseModel success(Object data, String message, Integer code){

        return new BaseModel(data, message, code);
    }

    public static BaseModel fail(Object data){

        return new BaseModel(data, FAIL, FAIL_CODE);
    }

    public static BaseModel fail(Object data, String message){

        return new BaseModel(data, message, FAIL_CODE);
    }

    public static BaseModel fail(Object data, String message, Integer code){

        return new BaseModel(data, message, code);
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
