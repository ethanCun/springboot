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
