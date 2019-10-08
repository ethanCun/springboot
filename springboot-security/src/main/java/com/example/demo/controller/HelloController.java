package com.example.demo.controller;

import com.example.demo.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser(String username, String password){

        System.out.println("username = " + username + " password = " + password);

        SysUser user = new SysUser();
        user.setUsername(username);

        //对密码进行加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public int login(String username, String password, HttpServletRequest request,
                     HttpServletResponse response){

        //springboot-security异常处理
        //UsernameNotFoundException（用户不存在）
        //DisabledException（用户已被禁用）
        //BadCredentialsException（坏的凭据）
        //LockedException（账户锁定）
        //AccountExpiredException （账户过期）
        //CredentialsExpiredException（证书过期）

        return 0;
    }

    @RequestMapping(value = "/login")
    public String login(){

        return "login";
    }

    @RequestMapping(value = "/register")
    public String register(){

        return "register";
    }

    @RequestMapping(value = "/index")
    public String index(){

        return "index";
    }

    // /error 是系统默认的映射  不能重名 否则报错：Ambiguous mapping. Cannot map 'basicErrorController' method
    @RequestMapping(value = "/myerror")
    public String error(){

        return "error";
    }

    @ResponseBody
    @RequestMapping(value = "/admin")
    public String admin(){

        return "hello admin";
    }

    @ResponseBody
    @RequestMapping(value = "/test")
    public String test(){

        return "hello test";
    }

    @ResponseBody
    @RequestMapping(value = "/basic")
    public String httpBasic(){

        return "http basic";
    }


    //==================== 测试注解 =====================
    //注解参考：https://www.jianshu.com/p/c159afb7bd4a
    /**
     * Spring Security默认是禁用注解的
     * 1.prePostEnabled：支持Spring EL表达式，开启后可以使用
     * @PreAuthorize：方法执行前的权限验证
     * @PostAuthorize：方法执行后再进行权限验证
     * @PreFilter：方法执行前对集合类型的参数或返回值进行过滤，移除使对应表达式的结果为false的元素
     * @PostFilter：方法执行后对集合类型的参数或返回值进行过滤，移除使对应表达式的结果为false的元素
     *
     * 2.secureEnabled : 开启后可以使用
     * @Secured：用来定义业务方法的安全性配置属性列表
     *
     * 3.jsr250Enabled ：支持JSR标准，开启后可以使用
     * @RolesAllowed：对方法进行角色验证
     * @DenyAll：允许所有角色调用
     * @PermitAll：不允许允许角色调用
     * */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    @RequestMapping(value = "/requireAdmin")
    public String requireAdmin(){

        return "require admin";
    }

    @PostAuthorize("hasRole('ROLE_HOME')")
    @ResponseBody
    @RequestMapping(value = "/requireHome")
    public String requireHome(){

        return "require home";
    }

    @PreAuthorize("hasRole('ROLE_VIEW')")
    @ResponseBody
    @RequestMapping(value = "/requireView")
    public String requireView(){

        return "require view";
    }

    @PreAuthorize(("hasRole('ROLE_TEST')"))
    @ResponseBody
    @RequestMapping(value = "/requireTest")
    public String requireTest(){

        return "require test";
    }

    //===================springboot-security 用户切换
    //参考链接：https://my.oschina.net/go4it/blog/1591720
    /**
     * impersonate: 扮演
     * SwitchUserFilter默认的切换账号的url为/login/impersonate，
     * 默认注销切换账号的url为/logout/impersonate，默认的账号参数为username
     *
     * 测试：
     *  admin拥有权限：ROLE_ADMIN ROLE_VIEW ROLE_TEST ROLE_HOME
     *  czy拥有权限：ROLE_TEST
     *  调用localhost:8080/login/impersonate/username=czy或者admin来切换用户
     *  切换完成之后调用requireAdmin requireView requireHome requireTest即可测试效果
     * */
    @ResponseBody
    @RequestMapping(value = "/userExchanged")
    public Object userExchange(){

        return SecurityContextHolder.getContext().getAuthentication();
    }
}
