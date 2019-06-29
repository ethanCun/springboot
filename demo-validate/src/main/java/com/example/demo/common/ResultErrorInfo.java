package com.example.demo.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.stream.Collectors;

public class ResultErrorInfo{

    /**
     * 不适用BindingResult接受数据时， 返回的数据：
     *
     * {
     *     "timestamp": "2019-06-28T08:03:44.613+0000",
     *     "status": 400,
     *     "error": "Bad Request",
     *     "errors": [
     *         {
     *             "codes": [
     *                 "NotBlank.user.password",
     *                 "NotBlank.password",
     *                 "NotBlank.java.lang.String",
     *                 "NotBlank"
     *             ],
     *             "arguments": [
     *                 {
     *                     "codes": [
     *                         "user.password",
     *                         "password"
     *                     ],
     *                     "arguments": null,
     *                     "defaultMessage": "password",
     *                     "code": "password"
     *                 }
     *             ],
     *             "defaultMessage": "密码不能为空",
     *             "objectName": "user",
     *             "field": "password",
     *             "rejectedValue": null,
     *             "bindingFailure": false,
     *             "code": "NotBlank"
     *         },
     *         {
     *             "codes": [
     *                 "NotBlank.user.username",
     *                 "NotBlank.username",
     *                 "NotBlank.java.lang.String",
     *                 "NotBlank"
     *             ],
     *             "arguments": [
     *                 {
     *                     "codes": [
     *                         "user.username",
     *                         "username"
     *                     ],
     *                     "arguments": null,
     *                     "defaultMessage": "username",
     *                     "code": "username"
     *                 }
     *             ],
     *             "defaultMessage": "姓名不能为空",
     *             "objectName": "user",
     *             "field": "username",
     *             "rejectedValue": null,
     *             "bindingFailure": false,
     *             "code": "NotBlank"
     *         }
     *     ],
     *     "message": "Validation failed for object='user'. Error count: 2",
     *     "path": "/adduser"
     * }
     *
     * */


    //使用BindingResult接受验证报错的信息
    public static String getErrorMessage(BindingResult bindingResult){

        StringBuilder stringBuilder = new StringBuilder();

        //字段报错信息拼接
        for(String s : bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toSet())){

            stringBuilder.append(s + ",");
        }

        if (stringBuilder.length() == 0){

            return "";
        }

        return stringBuilder.substring(0, stringBuilder.length()-1);
    }
}
