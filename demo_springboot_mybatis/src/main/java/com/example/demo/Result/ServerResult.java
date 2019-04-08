package com.example.demo.Result;

import java.io.Serializable;

public class ServerResult<T> implements Serializable {

    private static final long serialVersionUID = 7393154872520762719L;

    private T data;
    private String message;
    private int code;

    public ServerResult() {
    }

    public ServerResult(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    //自定义返回结果
    public static <T> ServerResult<T> result(T data, String message, int code){
        return new ServerResult<>(data, message, code);
    }

    //成功
    public static <T> ServerResult<T> success(T data){
        return new ServerResult<>(data, "success", 0);
    }

    //失败
    public static <T> ServerResult<T> failure(T data){
        return new ServerResult<>(data, "failure", -1);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
}
