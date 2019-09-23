package com.example.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

//根据key切换数据源
public class DynamicDataSource extends AbstractRoutingDataSource {

    //每次请求的时候都会调用这个方法
    @Override
    protected Object determineCurrentLookupKey() {

        return DynamicDataSourceHolder.getDataSource();
    }
}
