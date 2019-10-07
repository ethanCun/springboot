package com.example.demo.service.imp;

import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysPermission;
import com.example.demo.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    @SuppressWarnings("all")
    private SysUserMapper sysUserMapper;

    //返回 user和user拥有的权限功能
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);

        SysUser user = (SysUser)this.sysUserMapper.selectOneByExample(example);

        if (user == null){

            throw new UsernameNotFoundException("用户不存在");

        }else {

            //根据id查询指定用户的所有权限
            List<SysPermission> perms = this.sysUserMapper.findPermissionsByUserId(user.getId());

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            perms.forEach(perm ->{

                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(perm.getName());

                grantedAuthorities.add(grantedAuthority);
            });

            System.out.println("grantedAuthorities = " + grantedAuthorities);

            //AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()) 字符串分割
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    grantedAuthorities);
        }

    }

    @Override
    public int addUser(SysUser user) {

        return this.sysUserMapper.insert(user);
    }
}
