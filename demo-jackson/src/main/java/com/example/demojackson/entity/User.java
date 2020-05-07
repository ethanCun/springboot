package com.example.demojackson.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.catalina.startup.SetContextPropertiesRule;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "t_user")
//指定不返回类中的一个或者多个属性
//@JsonIgnoreProperties(value = {"id", "username"})
//属性值为null的不参与序列化
//@JsonInclude(JsonInclude.Include.NON_NULL)
//指定属性在 json 字符串中的顺序
//@JsonPropertyOrder(value = {"username", "id", "age"})
public class User {

    //指定某个属性不参与序列化
    //@JsonIgnore
    @Id
    private Integer id;

    //@JsonProperty(value = "name")
    private String username;

    private Integer age;

//    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

//    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

//    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;


    public void setId(Integer id) {
        this.id = id;
    }

    //@JsonSetter 标注于 setter 方法上，类似 @JsonProperty ，也可以解决 json 键名称和 java pojo 字段名称不匹配的问题
    //@JsonSetter(value = "name")
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}