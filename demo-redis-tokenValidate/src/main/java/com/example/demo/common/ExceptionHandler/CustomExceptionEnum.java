package com.example.demo.common.ExceptionHandler;

public enum CustomExceptionEnum {

    TokenIsNull("token不能为空", 101),
    TokenIsInvalidate("token已经过期", 102),
    AccountLoginOtherDervice("账号在其他设备上登录", 103),
    EncryptError("token加密出现错误", 104),
    DecryptError("token解密错线错误", 105),
    TokenValidateFailed("token验证失败", 106);

    public String message;
    public Integer code;

    CustomExceptionEnum(String message, Integer code){

        this.message = message;
        this.code = code;
    }

}
