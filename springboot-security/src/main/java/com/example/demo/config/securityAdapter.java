package com.example.demo.config;

import com.example.demo.service.UserService;
import com.example.demo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * 继承WebSecurityConfigurerAdapter抽象类，主要是最后配置Security，
 * 配置之前定义的拦截器、提供用户基本权限信息、以及一些访问控制。
 * <p>
 * springBoot-security中的过滤器:
 * 其中UsernamePasswordAuthenticationFilter过滤器用于处理基于表单方式的登录认证，
 * 而BasicAuthenticationFilter用于处理基于HTTP Basic方式的登录验证，
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法级别的权限认证
public class securityAdapter extends WebSecurityConfigurerAdapter {

    //记住密码
    @Autowired
    private DataSource dataSource;

    //请数据库数据交给spring security管理
    @Bean
    public UserService userServiceSecurity() {

        System.out.println("userServiceSecurity...");

        return new UserServiceImp();
    }


    //user Details Service验证 主要是对身份认证的设置
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
    public BCryptPasswordEncoder passwordEncoder() {

        //注册或者更改密码时的加密：
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密
//        String encodedPassword = bCryptPasswordEncoder.encode("密码");
        //保存到数据库

        return new BCryptPasswordEncoder();
    }


    //对请求路径中带css,img,js的请求内容不进行权限拦截 主要是对某些 web 静态资源的设置
    @Override
    public void configure(WebSecurity web) throws Exception {

        System.out.println("configure WebSecurity web...");

        //设置需要忽略的验证url
        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/images/**")
                .antMatchers("/register")
                .antMatchers("/addUser");
    }


    //复写这个方法来配置 {@link HttpSecurity}.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("configure http security...");

//        http.httpBasic() httpBasic
//        http.formLogin() formLogin

        //任何用户都可以访问以下URI
        http.headers().and().authorizeRequests().antMatchers("/login", "/", "/signIn", "register", "/addUser", "/myerror")
                .permitAll();

        //其他的uri都需要验证
        http.headers().and().authorizeRequests().anyRequest().authenticated();

        //记住密码
        //<input type="checkbox" name="remember-me" value="true">
        //前端通过控制value的值 设置是否记住密码
        http.authorizeRequests().and().rememberMe()
                .tokenValiditySeconds(30).tokenRepository(persistentTokenRepository());

        //表单请求
        http.formLogin().loginPage("/login").loginProcessingUrl("/signIn")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/myerror").permitAll();

        //注销 .permitAll()需要带上 表示登录页面都可以访问 不然会报错 访问不了
        http.formLogin().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .invalidateHttpSession(true).permitAll();

        //失效session
        http.sessionManagement().invalidSessionUrl("/login");

        //跨域
        http.csrf().disable();

//                http
//                .headers()
//                .and()
//                //授权配置
//                .authorizeRequests()
//                //所有请求
//                .anyRequest()
//                //都需要验证
//                .authenticated()
//                //登录路径 对应login方法
//                .and().formLogin()
//                .loginPage("/login") // 设置登录页面
//                .loginProcessingUrl("/signIn") // 自定义的登录接口
//                //登录成功后跳转到index页面
//                .defaultSuccessUrl("/index", true).permitAll()
//                //登录失败或者异常跳转页面
//                .failureUrl("/myerror").permitAll() //表示错误页面任意用户可以访问
//                //sesson失效跳转
//                .and().sessionManagement().invalidSessionUrl("/login")
//                //session失效时间
//                .and().rememberMe().tokenValiditySeconds(60).rememberMeParameter("remember-me") //其实默认就是remember-me，这里可以指定更换
//                .tokenRepository(persistentTokenRepository())// 配置数据库源
//                //掉线之后跳转的路径
//                .and().logout()
//                //触发注销的操作
//                .logoutUrl("/logout")
//                //注销成功后跳转的url
//                .logoutSuccessUrl("/login")
//                //在注销时让httpsession失效
//                .invalidateHttpSession(true)
//                .permitAll() //表示登录页面任意用户可以访问
//                //配置跨域请求
//                .and().csrf().disable();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();

        //将 DataSource 设置到 PersistentTokenRepository
        jdbcTokenRepository.setDataSource(dataSource);

        // 第一次启动的时候自动建表（可以不用这句话，自己手动建表，源码中有语句的）
        //jdbcTokenRepository.setCreateTableOnStartup(true);

        return jdbcTokenRepository;
    }
}
