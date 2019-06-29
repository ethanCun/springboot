package com.example.demo.controller;

import com.example.demo.common.BaseController;
import com.example.demo.common.BaseModel;
import com.example.demo.common.ResultErrorInfo;
import com.example.demo.entity.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

//@Validated： 开启数据有效性校验，添加在类上即为验证方法，添加在方法参数中即为验证参数对象。（添加在方法上无效）
@Validated
@Controller
public class UserContrller extends BaseController {

    private Map<String, Object> map = new HashMap<>();

    //普通验证：报500
    @ResponseBody
    @PostMapping(value = "/adduser3")
    public Map<String, Object> addUser3(String username, String password){

        if (username == null){
            throw new NullPointerException("姓名不能为空");
        }
        if (username.length() < 2 || username.length() > 5){
            throw  new RuntimeException("姓名长度在2-5之间");
        }
        if (password == null){
            throw new NullPointerException("密码不能为空");
        }
        if (password.length() < 2 || password.length() > 5){
            throw  new RuntimeException("密码长度在2-5之间");
        }

        map.clear();

        map.put("username", username);
        map.put("password", password);

        return map;
    }

    //500
    @ResponseBody
    @PostMapping(value = "/adduser2")
    public Map<String, Object> addUser2(@NotBlank(message = "姓名不能为空")
                                        @Length(min = 2, max = 5, message = "姓名长度在{min}-{max}个字符之间")
                                                String username,
                                        @NotBlank(message = "密码不能为空")
                                        @Length(min = 6, max = 10, message = "密码长度在{min}-{max}个字符之间")
                                                String password){

        map.clear();

        map.put("username", username);
        map.put("password", password);

        return map;
    }

    //添加在方法参数中即为验证参数对象。
    //400
    @ResponseBody
    @PostMapping(value = "/adduser1")
    public BaseModel addUser1(@Validated User user, BindingResult bindingResult){

        //接受校验结果
        if (bindingResult.hasErrors()){

            return BaseModel.fail(ResultErrorInfo.getErrorMessage(bindingResult), null);

        }else {

            return BaseModel.success(user);
        }

    }

    //基础校验返回的数据 不使用BindingResult接受错误信息
    @ResponseBody
    @RequestMapping(value = "/adduser")
    public BaseModel addUser(@Validated User user){

        return BaseModel.success(user);
    }

    //分组校验
    @ResponseBody
    @GetMapping(value = "/adduser5")
    public BaseModel addUser5(@Validated({User.Student.class}) User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){

            return new BaseModel(ResultErrorInfo.getErrorMessage(bindingResult), BaseModel.constants.fail, null);
        }else {

            return new BaseModel(BaseModel.constants.successMsg, BaseModel.constants.success, user);
        }
    }

    @ResponseBody
    @GetMapping(value = "/adduser6")
    public BaseModel addUser6(@Validated({User.Teacher.class}) User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){

            return new BaseModel(ResultErrorInfo.getErrorMessage(bindingResult), BaseModel.constants.fail, null);

        }else {

            return new BaseModel(BaseModel.constants.successMsg, BaseModel.constants.success, user);
        }
    }
}
