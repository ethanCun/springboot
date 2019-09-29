package com.example.demo.config;

import com.example.demo.service.UserService;
import com.example.demo.service.imp.UserServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * springBoot-security中的过滤器:
 * 其中UsernamePasswordAuthenticationFilter过滤器用于处理基于表单方式的登录认证，
 * 而BasicAuthenticationFilter用于处理基于HTTP Basic方式的登录验证，
 * */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法级别的权限认证
public class securityAdapter extends WebSecurityConfigurerAdapter {

    //请数据库数据交给springsecurity管理
    @Bean
    public UserService userServiceSecurity(){

        System.out.println("userServiceSecurity...");

        return new UserServiceImp();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        System.out.println("configure AuthenticationManagerBuilder auth...");

        auth.userDetailsService(userServiceSecurity()).passwordEncoder(passwordEncoder());

//        auth.inMemoryAuthentication().withUser("ethan")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("role1,role2,role3");
    }


    //报错，参考：https://blog.csdn.net/qq_43581949/article/details/90733834
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        //注册或者更改密码时的加密：
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密
//        String encodedPassword = bCryptPasswordEncoder.encode("密码");
        //保存到数据库

        return new BCryptPasswordEncoder();
    }

    //对请求路径中带css,img,js的请求内容不进行权限拦截
    @Override
    public void configure(WebSecurity web) throws Exception {

        System.out.println("configure WebSecurity web...");

        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("configure http security...");

//        http.httpBasic() httpBasic
//        http.formLogin() formLogin

        http.headers().and()
                //授权配置
                .authorizeRequests()
                //所有请求
                .anyRequest()
                //都需要验证
                .authenticated()
                //登录路径 对应login方法
                .and().formLogin().loginPage("/signIn")
                //跳转到登录页面 登录成功后跳转到index页面
                .loginProcessingUrl("/login").defaultSuccessUrl("/index", true)
                //登录失败跳转页面
                .failureUrl("/myerror").permitAll()
                //sesson失效跳转
                .and().sessionManagement().invalidSessionUrl("/login")
                //session失效时间
                .and().rememberMe().tokenValiditySeconds(10000)
                //掉线之后跳转的路径
                .and().logout().logoutSuccessUrl("/login").permitAll()
                //配置跨域请求
                .and().csrf().disable();
    }
}
