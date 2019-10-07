package com.example.demo.config;

import com.example.demo.dao.SysPermissionMapper;
import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 实现了FilterInvocationSecurityMetadataSource接口，类似Holder主要实现加载缓存权限功能路径和名称，
 * 以及提供从请求路径查找权限名称，供后续权限决策管理器去判定使用。
 * */
@Service
public class MyInvocationSecurityMetadataSourceService
        implements FilterInvocationSecurityMetadataSource {

    @Autowired
    @SuppressWarnings("all")
    private SysUserMapper sysUserMapper;

    @Autowired
    @SuppressWarnings("all")
    private SysPermissionMapper sysPermissionMapper;

    //缓存
    private HashMap<String, Collection<ConfigAttribute>> map = null;

    //加载权限表中的所有权限
    public void loadPerms(){

        map = new HashMap<>();
        Collection<ConfigAttribute> attributes;
        ConfigAttribute configAttribute;

        List<SysPermission> perms = this.sysPermissionMapper.selectAll();

        for (SysPermission perm : perms) {

            attributes = new ArrayList<>();

            // 此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。
            // 此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            configAttribute = new SecurityConfig(perm.getName());

            attributes.add(configAttribute);

            // 用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value
            map.put(perm.getUrl(), attributes);
        }
    }


    // 此方法是为了判定请求的url是否在权限表中，
    // 如果在权限表中，则返回给 decide 方法，调用decide方法,用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        if (map == null){
            loadPerms();
        }

        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation)object).getHttpRequest();

        AntPathRequestMatcher matcher;
        String resUrl;

        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); ){

            resUrl = iterator.next();
            matcher = new AntPathRequestMatcher(resUrl);

            System.out.println("resUrl = " + resUrl + " request = " + request.getRequestURL());

            if (matcher.matches(request)){

                System.out.println("match...");
                return map.get(resUrl);
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
