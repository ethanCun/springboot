package com.example.demo.common.Config;

import com.example.demo.common.interceptors.AuthenticationInteceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//加载验证拦截器
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加token验证拦截器
        registry.addInterceptor(authenticationInteceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public AuthenticationInteceptor authenticationInteceptor(){

        return new AuthenticationInteceptor();
    }
}
