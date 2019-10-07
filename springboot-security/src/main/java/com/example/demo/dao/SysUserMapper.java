package com.example.demo.dao;

import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysUser;
import com.example.demo.mapper.IMapper;

import java.util.List;

public interface SysUserMapper extends IMapper<SysUser> {

    //根据用户id查找所有的权限名称
    List<SysPermission> findPermissionsByUserId(int id);
}