package com.example.shiro.demo9.web;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index"})
    public String index(){

        return "index";
    }

    @RequestMapping(value = "/403")
    public String unauthorized(){

        return "403";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Map<String, Object> map, Model model)
            throws Exception{

        System.out.println("....");

        // 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exception = (String) request.getAttribute("shiroLoginFailure");

        String errorMsg = "";

        if (exception != null){

            if (UnknownAccountException.class.getName().equals(exception)){

                //账户名不存在
                errorMsg = "账户名不存在";

            }else if (IncorrectCredentialsException.class.getName().equals(exception)){

                errorMsg = "账号或者密码不对";

            }else{

                errorMsg = "其他错误：" + exception;
            }
        }

        map.put("msg", errorMsg);
        model.addAttribute("errorMsg", errorMsg);

        return "/login";
    }

}
