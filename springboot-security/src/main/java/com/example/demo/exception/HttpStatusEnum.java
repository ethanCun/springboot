package com.example.demo.exception;

//异常枚举类
public enum HttpStatusEnum {

    TokenIsNull("token不能为空", 101),
    TokenIsNotValid("token验证失败", 102),
    TokenRequiredRelogin("你的账号在其他地方登录", 103),
    UserIsNotExist("用户不存在", 104),
    UsernamePasswordNotRight("用户名或者密码错误", 105),
    UsernameDisabled("用户名已被禁用", 106),
    UsernameLocked("用户名已被锁定", 107),
    UsernameExpired("用户名过期", 108),
    CredenCtialExpired("证书过期", 109);

    public String message;
    public Integer code;

    HttpStatusEnum(String message, Integer code){

        this.message = message;
        this.code = code;
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
