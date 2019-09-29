package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

//存放数据源对应的key
public class DynamicDataSourceHolder {

    //线程本地环境
    private static final ThreadLocal<String> contextHolders =
            new ThreadLocal<>();

    //数据源列表
    private static List<String> dataSourceIds =
            new ArrayList<>();

    //设置数据源
    public static void setDataSource(String customerType){

        contextHolders.set(customerType);
    }

    //获取数据源
    public static String getDataSource(){

        return (String)contextHolders.get();
    }

    //清除数据源
    public static void cleanDataSource(){

        contextHolders.remove();
    }

    //判断指定的数据源是否存在
    public static boolean containsDataSource(String dataSourceId){

        return dataSourceIds.contains(dataSourceId);
    }
}
