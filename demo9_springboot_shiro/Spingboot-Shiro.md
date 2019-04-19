#### application.yml
```

spring:
    datasource:
      url: jdbc:mysql://localhost:3306/test
      username: root
      password: czy1212
      driver-class-name: com.mysql.cj.jdbc.Driver

    jpa:
      database: mysql
      show-sql: true
      hibernate:
        ddl-auto: update
        naming:
          strategy: org.hibernate.cfg.DefaultComponentSafeNamingStrategy
      properties:
         hibernate:
            dialect: org.hibernate.dialect.MySQL5Dialect

    thymeleaf:
       cache: false
       mode: LEGACYHTML5


    devtools:
      restart:
        enabled: true #开启热部署
        additional-paths: src/main/java #热部署目录

server:
  port: 8888
```
#### entity
##### UserInfo
```
package com.example.shiro.demo9.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue
    private Integer uid;

    @Column(unique = true)
    private String username;

    private String name;

    private String password;

    private String salt;

    private byte state;

    /*
     * @ManyToMany 注释表示UserInfo 是多对多关系的一端。
     * @JoinTable 描述了多对多关系的数据表关系，name属性指定中间表名称。
     * joinColumns 定义中间表与UserInfo 表的外键关系，
     * 中间表SysUserRole的uid 列是UserInfo 表的主键列对应的外键列。
     * inverseJoinColumns 属性定义了中间表与另外一端(SysROle)的外键关系。
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole",
    joinColumns = {@JoinColumn(name="uid")},
    inverseJoinColumns = {@JoinColumn(name="roleId")})
    private List<SysRole> roleList;


     /*
      *  密码盐
      *
      * @author ethan
      * @date 2019/4/3 6:30 PM
      * @param
      * @return
      */
     public String getCredentialSalt(){
         return this.username+this.salt;
     }


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
```
##### SysRole
```
package com.example.shiro.demo9.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SysRole implements Serializable {

    private static final long serialVersionUID = -6059889886728307291L;

    @Id
    @GeneratedValue
    private Integer id;

    private Integer avaliable;

    private String description;

    private String role;

     /*
      *  角色对应的用户列表
      */
     @ManyToMany
     @JoinTable(name = "SysUserRole",
     joinColumns = {@JoinColumn(name="roleId")},
     inverseJoinColumns = {@JoinColumn(name = "uid")})
     private List<UserInfo> userInfos;

     /**
      * 角色对应的权限列表
      * */
     @ManyToMany(fetch = FetchType.EAGER)
     @JoinTable(name = "SysRolePermission",
     joinColumns = {@JoinColumn(name = "roleId")},
     inverseJoinColumns = {@JoinColumn(name = "permissionId")})
     private List<SysPermission> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Integer avaliable) {
        this.avaliable = avaliable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
```
##### SysPermisson
```
package com.example.shiro.demo9.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SysPermission implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer available;
    private String name;
    private Integer parent_id;
    private String parent_ids;
    private String permission;
    private String url;

    @Column(columnDefinition = "enum('menu', 'button')")
    private String resourceType;

    /**
     * 权限对应的角色
     * */
    @ManyToMany
    @JoinTable(name = "SysRolePermission",
    joinColumns = {@JoinColumn(name = "permissionId")},
    inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> sysRoleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parent_ids) {
        this.parent_ids = parent_ids;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }


    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }
}

```
#### 自定义Realm
```
package com.example.shiro.demo9.config;

import com.example.shiro.demo9.model.SysPermission;
import com.example.shiro.demo9.model.SysRole;
import com.example.shiro.demo9.model.UserInfo;
import com.example.shiro.demo9.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        // SimpleAuthorizationInfo进行角色的添加和权限的添加。
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        UserInfo userInfo = (UserInfo)principals.getPrimaryPrincipal();

        for(SysRole role:userInfo.getRoleList()){

            authorizationInfo.addRole(role.getRole());

            for(SysPermission p:role.getPermissions()){


                authorizationInfo.addStringPermission(p.getPermission());
            }
        }

        System.out.println(authorizationInfo);

        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

        //获取用户的输入的账号
        String username = (String)token.getPrincipal();
        System.out.println(username);

        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUserName(username);
        System.out.println("----->>userInfo="+userInfo);

        if(userInfo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}
```
#### Shiro配置
```
package com.example.shiro.demo9.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        System.out.println("ShiroConfiguration.shirFilter()");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login"); //登录失败后默认跳转到登录地址去
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");//登录成功后跳转地址
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    //spring-shiro.xml中通常会加aop配置，
    //以使shiro认证注解（@RequiresPermissions、@RequiresRoles、@RequiresUser、@RequiresGuest）work。
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    //SpringMVC的异常处理,SimpleMappingExceptionResolver只能简单的处理异常
    //当发生异常的时候,根据发生的异常类型跳转到指定的页面来显示异常信息
    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {

        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException","403");
        r.setExceptionMappings(mappings);  // None by default

        r.setDefaultErrorView("error");    // No default
        //跳转时携带的信息
        r.setExceptionAttribute("ex");     // Default is "exception"
        r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }
}
```
#### dao层
```
package com.example.shiro.demo9.dao;

import com.example.shiro.demo9.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoDao extends CrudRepository<UserInfo, Long> {

    //通过用户名称查询用户信息
    public UserInfo findByUsername(String userName);
}
```
#### service & imp
```
package com.example.shiro.demo9.service.imp;

import com.example.shiro.demo9.dao.UserInfoDao;
import com.example.shiro.demo9.model.UserInfo;
import com.example.shiro.demo9.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImp implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUserName(String username) {

        UserInfo userInfo = userInfoDao.findByUsername(username);

        return userInfoDao.findByUsername(username);
    }
}

```
#### HomeController
```
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
```
#### UserInfoController
```
package com.example.shiro.demo9.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @RequestMapping(value = "/userList")
    @RequiresPermissions("userInfo:view")
    public String userInfo(){

        return "userInfo";
    }

    @RequestMapping(value = "/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd(){

        return "userInfoAdd";
    }

    @RequestMapping(value = "/userDelete")
    @RequiresPermissions("userInfo:del")
    public String userInfoDelete(){

        return "userInfoDelete";
    }
}

```
#### 入口
```
package com.example.shiro.demo9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo9Application {

    public static void main(String[] args) {

        SpringApplication.run(Demo9Application.class, args);
    }

}
```