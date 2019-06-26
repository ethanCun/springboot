package com.example.demo.common;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.LogManager;

@Configuration
@MapperScan(basePackages = "com.example.mapper.*")
public class mybatisplusConfig {

    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        paginationInterceptor.setDialectType("mysql");

        return paginationInterceptor;
    }
}
