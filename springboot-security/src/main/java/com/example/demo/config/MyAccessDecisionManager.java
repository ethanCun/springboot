package com.example.demo.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * 实现了AccessDecisionManager接口，主要判定用户是否拥有权限的决策方法，有权限放行，无权限拒绝访问。
 * */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    // decide 方法是判定是否拥有权限的决策方法，
    // authentication 是UserServiceImp中循环添加到 GrantedAuthority 对象中的权限信息集合.
    // object 包含客户端发起的请求的requset信息，
    // 可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
    // configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
    // 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。
    // 如果不在权限表中则放行,不会执行这个方法
    @Override
    public void decide(Authentication authentication,
                       Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        System.out.println("decide...");

        if (null == configAttributes || configAttributes.size() <= 0){
            return;
        }

        ConfigAttribute configAttribute;
        String needRole;

        for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); ((Iterator) iter).hasNext();){

            configAttribute = iter.next();

            needRole = configAttribute.getAttribute();

            //authentication 为 UserServiceImp 中循环添加到 GrantedAuthority 对象中的权限信息集合
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {

                System.out.println("needRole = " + needRole + " grantedAuthority.getAuthority() = "
                + grantedAuthority.getAuthority());

                if (needRole.trim().equals(grantedAuthority.getAuthority())){
                    return;
                }
            }
        }

        throw new AccessDeniedException("没有权限");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
}
