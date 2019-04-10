package com.springboot.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    //序列化与反序列化时注意entity需要实现Serializable：
    //否则报错：Failed to serialize object using DefaultSerializer
    private static final long serialVersionUID = -6911773122916716074L;

    private int id;

    private String createDate;

    //修改时间
    private String updateDate;

    private String name;

    private int age;

//    public User() {
//    }
//
//    public User(int id, String createDate, String updateDate, String name, int age) {
//        this.id = id;
//        this.createDate = createDate;
//        this.updateDate = updateDate;
//        this.name = name;
//        this.age = age;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        this.createDate = createDate;
//    }
//
//    public String getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(String updateDate) {
//        this.updateDate = updateDate;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
}
