package com.example.demo.model;

public class BaseModel {

    public static final class constants{

        public constants(){}

        public static int success=0;
        public static int fail=-1;

        public static String successMsg = "success";
        public static String failMsg = "fail";
    }


    public String message;
    public int code;
    public Object data;

    public BaseModel(){}

    public BaseModel(String message, int code, Object data){

        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static BaseModel success(Object data){

        return new BaseModel(constants.successMsg, constants.success, data);
    }

    public static BaseModel success(String message, Object data){

        return new BaseModel(message, constants.success, data);
    }

    public static BaseModel success(int code, String message, Object data){

        return new BaseModel(message, code, data);
    }

    public static BaseModel fail(Object data){

        return new BaseModel(constants.failMsg, constants.fail, data);
    }

    public static BaseModel fail(String message, Object data){

        return new BaseModel(message, constants.fail, data);
    }

    public static BaseModel fail(int code, String message, Object data){

        return new BaseModel(message, code, data);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
