package com.example.demo.entity;

public class User {

    private int id;
    private String userName;
    private String nickName;
    private String passWord;
    private String userSex;

    public User() {
    }

    public User(int id, String userName, String nickName, String passWord, String userSex) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.passWord = passWord;
        this.userSex = userSex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
