package com.example.demo.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * @Target:注解的作用目标
 * @Target(ElementType.TYPE)——接口、类、枚举、注解
 * @Target(ElementType.FIELD)——字段、枚举的常量
 * @Target(ElementType.METHOD)——方法
 * @Target(ElementType.PARAMETER)——方法参数
 * @Target(ElementType.CONSTRUCTOR) ——构造函数
 * @Target(ElementType.LOCAL_VARIABLE)——局部变量
 * @Target(ElementType.ANNOTATION_TYPE)——注解
 * @Target(ElementType.PACKAGE)——包
 *
 * @Retention：注解的保留位置
 * RetentionPolicy.SOURCE:这种类型的Annotations只在源代码级别保留,编译时就会被忽略,在class字节码文件中不包含。
 * RetentionPolicy.CLASS:这种类型的Annotations编译时被保留,默认的保留策略,在class文件中存在,但JVM将会忽略,运行时无法获得。
 * RetentionPolicy.RUNTIME:这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用。
 * @Document：说明该注解将被包含在javadoc中
 * @Inherited：说明子类可以继承父类中的该注解
 *
 * 简单自定义一个实体类User, 使用lombok简化实体类的编写
 * */

public class User implements Serializable {

    private static final long serialVersionUID = 3144826529256804010L;

    private Integer id = 0;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能少于{min}个字符")
    private String password;

    private String token = "";

    public User(String username, String password){

        this.id = 0;
        this.token = "";
        this.username = username;
        this.password = password;
    }

    //生成token的方法
    public String getToken(User user){

        //Algorithm.HMAC256(): 使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
        //withAudience(): 存入需要保存在token的信息，这里我把用户ID存入token中
        //withExpiresAt: 测试1分钟后token过期
        return JWT.create()
                .withAudience(user.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60*1000*1))
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public User() {
    }

    public User(Integer id, @NotNull(message = "用户名不能为空") String username, @NotNull(message = "密码不能为空") @Length(min = 6, message = "密码长度不能少于{min}个字符") String password, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
