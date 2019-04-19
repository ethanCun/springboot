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

