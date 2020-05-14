package com.example.demoaoplog.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//SpringBoot拦截器获取Request的body数据
//问题分析：由于拦截器中，request中getReader()和getInputStream()只能调用一次，到controller里数据就为空了。
//解决办法：重写HttpServletRequestWrapper把request保存下来，然后通过过滤器把保存下来的request再填充进去
@Component
@Configurable
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean repeatedlyReadFilter(){

        FilterRegistrationBean registration = new FilterRegistrationBean();
        RepeatedlyReadFilter repeatedlyReadFilter = new RepeatedlyReadFilter();
        registration.setFilter(repeatedlyReadFilter);
        registration.addUrlPatterns("/*");

        return registration;
    }
}
