package com.example.demo.config;

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

//配置数据库1
@Configuration
//指定该SqlSession对象对应的dao(basePackages , dao扫包  sqlSessionFactoryRef: SqlSessionFactory对象注入到该变量中)
@MapperScan(basePackages = "com.example.demo.dao.test1",
sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class Test1DataSourceConfig {

    //封装数据源对象创建, 该方法就已经将数据源的各个数据封装到该对象中
    @Primary //指定启动是默认使用的数据源
    @Bean(name = "test1DataSource")
    //读取数据源前缀
    @ConfigurationProperties(prefix = "test1.spring.datasource")
    public DataSource dataSource(){

        return DataSourceBuilder.create().build();
    }

    //创建sqlsessionfactory
    @Bean(name = "test1SqlSessionFactory")
    @Primary
    public SqlSessionFactory test1Factory(
            @Qualifier("test1DataSource") DataSource dataSource)
            throws Exception{

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        //指定起别名的包
        bean.setTypeAliasesPackage("com.example.demo.entity");

        bean.setDataSource(dataSource);

        //指定该SqlSession对应的mapper.xml文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/test1/*.xml"));

        return bean.getObject();
    }

    //创建事务
    @Bean(name = "test1TransactionManager")
    @Primary
    public DataSourceTransactionManager test1TransactionManager(
            @Qualifier("test1DataSource") DataSource dataSource
    ) throws Exception{
        return new DataSourceTransactionManager(dataSource);
    }

    //创建sqlSessionTemplate
    @Bean(name = "test1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate test1SqlSessionTemplate(
            @Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory
    ) throws Exception{

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
