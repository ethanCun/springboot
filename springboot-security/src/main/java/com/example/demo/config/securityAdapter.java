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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

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
@EnableWebSecurity //开启spring security功能
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法级别的权限认证
public class securityAdapter extends WebSecurityConfigurerAdapter {

    //记住密码
    @Autowired
    private DataSource dataSource;

    //请数据库数据交给spring security管理
    @Bean
    public UserService userServiceSecurity() {

        return new UserServiceImp();
    }


    //user Details Service验证 主要是对身份认证的设置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userServiceSecurity()).passwordEncoder(passwordEncoder());

        //指定内存认证 auth.inMemoryAuthentication() 指定用户名密码角色
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

        //设置需要忽略的验证url
        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/images/**")
                .antMatchers("/register")
                .antMatchers("/addUser");
    }


    //复写这个方法来配置HttpSecurity
    //httpsecurity使用参考:https://blog.csdn.net/dawangxiong123/article/details/68960041
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //解决不允许显示在iframe的问题
        http.headers().frameOptions().disable();

        //指定角色为ROLE_USER 可以针对多个url配置多个限定角色
        //http.authorizeRequests().antMatchers("/").hasRole("USER");

        //任何用户都可以访问以下URI http.headers().disable():取消安全报文头
        http.headers().and().authorizeRequests()
                .antMatchers("/login", "/", "/signIn", "register", "/addUser", "/myerror")
                .permitAll();

        //其他的uri都需要验证
        http.headers().and().authorizeRequests().anyRequest().authenticated();

        //配置匿名访问
        http.anonymous().authorities("ROLE_ANON");

        //记住密码
        //<input type="checkbox" name="remember-me" value="true">
        //前端通过控制value的值 设置是否记住密码
        http.authorizeRequests().and().rememberMe()
                .tokenValiditySeconds(3600).tokenRepository(persistentTokenRepository());

        //表单请求
        // 默认
        // loginPage: /login with an HTTP get
        // usernameParameter: username
        // passwordParameter: password
        // failureUrl: /login?error
        // loginProcessingUrl: /login with an HTTP post
        http.formLogin().loginPage("/login").loginProcessingUrl("/signIn")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/myerror").permitAll();

        //http基本认证
        // 例子： 此处[ROLE_ADMIN, ROLE_VIEW]不包含ROLE_HOME 所以访问basic端口不会返回内容
        http.authorizeRequests().antMatchers("/**").hasRole("HOME").and().httpBasic();

        //注销 .permitAll()需要带上 表示登录页面都可以访问 不然会报错 访问不了
        http.formLogin().and().logout().deleteCookies("remove")
                .logoutUrl("/logout").logoutSuccessUrl("/login")
                .invalidateHttpSession(true).permitAll();

        //session失效后跳转到/login
        http.sessionManagement().invalidSessionUrl("/login");

        //maximumSessions(1)当前只有一个用户登录 再登陆时会被第一个用户会被挤下去
        //当使用SessionManagementConfigurer的maximumSessio(int)时不要忘记为应用配置HttpSessionEventPublisher，
        // 这样能保证过期的session能够被清除。
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");

        //配置每个请求都为https
        //http.requiresChannel().anyRequest().requiresSecure();

        //禁止跨域请求
        http.csrf().disable();

        //配置端口跳转：配置PortMapper 下面的例子将从8080跳转到https端口8443
        // 并且将http端口80跳转到https443端口。
        //http.portMapper().http(8080).mapsTo(8443)
                //.http(80).mapsTo(443);

        //切换用户 表示只有admin的管理员才有切换用户的权限
        http.authorizeRequests().antMatchers("/").hasRole("ADMIN")
                .and().addFilterAfter(switchUserFilter(), FilterSecurityInterceptor.class);
    }


    //token保存到数据库
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();

        //将 DataSource 设置到 PersistentTokenRepository
        jdbcTokenRepository.setDataSource(dataSource);

        // 第一次启动的时候自动建表（可以不用这句话，自己手动建表，源码中有语句的）
        //jdbcTokenRepository.setCreateTableOnStartup(true);

        return jdbcTokenRepository;
    }


    //SpringSecurity内置的session监听器
    //SESSION 并发管理, 确保同账号只允许登录一次
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {

        return new HttpSessionEventPublisher();
    }

    //切换用户 参考链接：https://my.oschina.net/go4it/blog/1591720
    @Bean
    public SwitchUserFilter switchUserFilter() throws Exception{

        SwitchUserFilter switchUserFilter = new SwitchUserFilter();

        switchUserFilter.setUserDetailsService(userDetailsService());

        //切换后用户后调用的url
        switchUserFilter.setTargetUrl("/userExchanged");

        return switchUserFilter;
    }
}
