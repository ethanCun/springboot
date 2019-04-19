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
