package com.example.demo.common.Base;

public class BaseModel {

    public static String successMsg = "success";
    public static String failMsg = "fail";

    public static Integer successCode = 0;
    public static Integer failCode = -1;

    public String msg;
    public Integer code;
    public Object data;

    public BaseModel(){}

    public BaseModel(Object data, String msg, Integer code){
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static BaseModel success(String msg){
        return new BaseModel(null, successMsg, successCode);
    }

    public static BaseModel success(Object data, String msg){
        return new BaseModel(data, msg, successCode);
    }

    public static BaseModel success(Object data, String msg, Integer code){
        return new BaseModel(data, msg, code);
    }

    public static BaseModel fail(String msg){
        return new BaseModel(null, failMsg, failCode);
    }

    public static BaseModel fail(Object data, String msg){
        return new BaseModel(data, msg, failCode);
    }

    public static BaseModel fail(Object data, String msg, Integer code){
        return new BaseModel(data, msg, code);
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
