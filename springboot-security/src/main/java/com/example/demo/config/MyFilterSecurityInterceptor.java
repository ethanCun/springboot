package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

/**
 * 继承AbstractSecurityInterceptor抽象类，权限管理Security真正的拦截器，
 * 并绑定了MyAccessDecisionManager（处理权限认证）和MyInvocationSecurityMetadataSourceService
 * （提供请求路径和权限名称元数据）
 * */
@Service
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    //装配FilterInvocationSecurityMetadataSource
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    //装配MyAccessDecisionManager
    @Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager accessDecisionManager) {

        System.out.println("setMyAccessDecisionManager");
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);

        invoke(filterInvocation);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException{

        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {

            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());

        }finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}
