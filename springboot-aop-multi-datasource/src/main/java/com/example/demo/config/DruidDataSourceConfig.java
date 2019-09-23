package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = {"com.example.demo.mapper.test1",
        "com.example.demo.mapper.test2"},
        sqlSessionFactoryRef = "sqlSessionFactory")
public class DruidDataSourceConfig {

    //配置别名
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasPackage;

    //配置mapper扫描位置
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    //mybatis配置位置
//    @Value("${mybatis.config-location}")
//    private String configLocation;


    //配置数据源1
    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource oneDataSource(){

        return new DruidDataSource();
    }

    //配置数据源2
    @Bean(name = "twoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource twoDataSource(){

        return new DruidDataSource();
    }

    //数据源管理 目标数据库 以及默认加载的数据库
    @Bean
    public DataSource dynamicDataSource() throws SQLException{

        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("oneDataSource", oneDataSource());
        targetDataSources.put("twoDataSource", twoDataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);

        dynamicDataSource.setDefaultTargetDataSource(oneDataSource());

        return dynamicDataSource;
    }

    //配置sqlSessionFactory
    //只会加载一次 下次请求的时候根据dynamicDataSource动态加载数据库
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource)
        throws Exception{

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        bean.setTypeAliasesPackage(typeAliasPackage);
//        bean.setConfigLocation(configLocation);

        return bean.getObject();
    }

    //配置事务
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource){

        return new DataSourceTransactionManager(dataSource);
    }
}
