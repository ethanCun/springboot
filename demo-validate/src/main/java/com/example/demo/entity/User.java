package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * JSR提供的校验注解（Java Specification Requests的缩写，意思是Java 规范提案）:
 * @Null 被注释的元素必须为 null
 * @NotNull 被注释的元素必须不为 null
 * @AssertTrue 被注释的元素必须为 true
 * @AssertFalse 被注释的元素必须为 false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
 * ---------------------
 *
 * Hibernate Validator提供的校验注解：
 * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 * @Email 被注释的元素必须是电子邮箱地址
 * @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
 * @NotEmpty 被注释的字符串的必须非空
 * @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
 *
 * */

public class User {

    //-------- 基础校验 有分组校验的情况下需要指定群组groups
    @NotBlank(message = "姓名不能为空")
    @Length(min = 2, max = 5, message = "姓名长度为{min}-{max}个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 10, message = "密码长度在{min}-{max}之间")
    private String password;

    //Integer Double类型为"",则在@InitBinder处理为0 String:@NotBlank
    @Range(min = 3, max = 25, message = "学生年龄在{min}-{max}之间", groups = {Student.class})
    private Integer age;

    @Min(groups = {Student.class}, value = 0, message = "成绩不能小于0")
    private Double score;

    //@NotEmpty只能作用在集合上 string collection
    @NotNull(message = "生日不能为空", groups = {Student.class, Teacher.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    //-------- 模拟分组校验情景：用户有学生和老师群体，只有学生需要校验age 老师不要校验age
    public interface Student{}
    public interface Teacher{}

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(){}

    public User(@NotBlank(message = "姓名不能为空", groups = {Student.class, Teacher.class}) @Length(min = 2, max = 5, message = "姓名长度为{min}-{max}个字符", groups = {Student.class, Teacher.class}) String username, @NotBlank(message = "密码不能为空", groups = {Student.class, Teacher.class}) @Length(min = 6, max = 10, message = "密码长度在{min}-{max}之间", groups = {Student.class, Teacher.class}) String password, @NotNull(message = "年龄不能为空", groups = {Student.class}) @Range(min = 3, max = 25, message = "学生年龄在{min}-{max}之间", groups = {Student.class}) Integer age, @NotNull(message = "成绩不能为空", groups = {Student.class}) @Min(groups = {Student.class}, value = 0, message = "成绩不能小于0") Double score, Date birthday) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.score = score;
        this.birthday = birthday;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
