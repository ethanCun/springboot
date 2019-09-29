package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.demo.dao.test2",
sqlSessionTemplateRef = "test2SqlSessionTemplate")
public class Test2DataSourceConfig {

    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "test2.spring.datasource")
    public DataSource dataSource(){

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("test2DataSource")
                                               DataSource dataSource) throws Exception{

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setTypeAliasesPackage("com.example.demo.entity");
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test2/*.xml"));

        return bean.getObject();
    }

    //创建事务
    @Bean(name = "test2TransactionManager")
    @Primary
    public DataSourceTransactionManager test1TransactionManager(
            @Qualifier("test2DataSource") DataSource dataSource
    ) throws Exception{
        return new DataSourceTransactionManager(dataSource);
    }

    //创建sqlSessionTemplate
    @Bean(name = "test2SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate test1SqlSessionTemplate(
            @Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory
    ) throws Exception{

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
