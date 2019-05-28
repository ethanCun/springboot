#### pom引入包和mybatis-generator-maven-plugin
```
   <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.1</version>
        </dependency>

        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.15</version>
            <scope>runtime</scope>
        </dependency>

        <!--druid-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.0</version>
        </dependency>

        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--jpa-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.7</version>
                <executions>
                    <execution>
                        <id>Generate MyBatis Artifacts</id>
                        <phase>deploy</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>

                <configuration>

                    <!--generator 工具配置文件的位置 -->
                    <configurationFile>src/main/resources/mybatis-generator/generatorConfig.xml</configurationFile>
                    <verbose>true</verbose>
                    <overwrite>true</overwrite>

                </configuration>

                <dependencies>

                    <!--mysql-->
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.15</version>
                        <scope>runtime</scope>
                    </dependency>

                    <dependency>
                        <groupId>org.mybatis.generator</groupId>
                        <artifactId>mybatis-generator-core</artifactId>
                        <version>1.3.7</version>
                    </dependency>

                </dependencies>
            </plugin>

        </plugins>
    </build>
```
#### application.yml
```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/test
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: czy1212
    type: com.alibaba.druid.pool.DruidDataSource
mybatis:
  type-aliases-package: com.example.demo
  configuration:
    map-underscore-to-camel-case: true
  mapper-locations: classpath:mapper/**/*.xml
```

#### mybatis-generator 部分字段定义 mybatisGeneratorInit.properties
```
#dao类和实体类的位置
project=src/main/java
#mapper文件的位置
resource=src/main/resources

#根据数据库中的表生成对应的pojo类、dao、mapper
jdbc_driver=com.mysql.cj.jdbc.Driver
jdbc_url=jdbc:mysql://localhost:3306/test
jdbc_username=root
jdbc_password=czy1212
```

#### mybatis-generator配置文件
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<!--配置生成器-->
<generatorConfiguration>

    <!--执行generator插件生成文件的命令： call mvn mybatis-generator:generate -e-->
    <!--引入配置文件 -->
    <properties resource="mybatis-generator/mybatisGeneratorInit.properties"></properties>

    <!--classPathEntry:数据库的JDBC驱动,换成你自己的驱动位置 可选-->
    <!--<classPathEntry location="D:\generator_mybatis\mysql-connector-java-5.1.24-bin.jar" /> -->


    <!--一个数据库一个context-->
    <!--defaultModelType="flat" 大数据字段，不分表-->
    <context id="MysqlTables" targetRuntime="MyBatis3Simple" defaultModelType="flat">

        <!--自动识别数据库关键字，默认false，如果设置为true，根据SqlReservedWords中定义的关键字列表；
        一般保留默认值，遇到数据库关键字（Java关键字），使用columnOverride覆盖-->
        <property name="autoDelimitKeywords" value="true"></property>

        <!--生成的Java文件的编码 -->
        <property name="javaFileEncoding" value="utf-8"></property>

        <!--beginningDelimiter和endingDelimiter：指明数据库的用于标记数据库对象名的符号，比如ORACLE就是双引号，MYSQL默认是`反引号；-->
        <property name="beginningDelimiter" value="`"></property>
        <property name="endingDelimiter" value="`"></property>
        
        <!--格式化java代码-->
        <property name="javaFormatter" value="org.mybatis.generator.api.dom.DefaultJavaFormatter"></property>
        <!--格式化XML代码-->
        <property name="xmlFormatter" value="org.mybatis.generator.api.dom.DefaultXmlFormatter"></property>
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin"></plugin>
        <plugin type="org.mybatis.generator.plugins.ToStringPlugin"></plugin>

        <!--注释-->
        <commentGenerator>
            <!--是否取消注释-->
            <property name="suppressAllComments" value="false"></property>
            <!--是否生成注释时间戳-->
            <property name="suppressDate" value="true"></property>
        </commentGenerator>

        <!--jdbc连接-->
        <jdbcConnection driverClass="${jdbc_driver}"
                        connectionURL="${jdbc_url}"
                        userId="${jdbc_username}"
                        password="${jdbc_password}"></jdbcConnection>

        <!--类型转化-->
        <javaTypeResolver>

            <!--是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.）-->
            <property name="forceBigDecimals" value="false"></property>
        </javaTypeResolver>

        <!--生成实体类地址-->
        <javaModelGenerator targetPackage="com.example.demo.domain" targetProject="${project}">

            <property name="enableSubPackages" value="false"></property>
            <property name="trimStrings" value="true"></property>

        </javaModelGenerator>

        <!-- 生成mapxml文件-->
        <sqlMapGenerator targetPackage="mapper" targetProject="${resource}">
            <property name="enableSubPackages" value="false"></property>
        </sqlMapGenerator>

        <!--生成mapxml对应client，也就是接口dao-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.example.demo.dao"
                             targetProject="${project}">
            <property name="enableSubPackages" value="false"></property>
        </javaClientGenerator>

        <!--
        table可以有多个,每个数据库中的表都可以写一个table，tableName表示要匹配的数据库表,
        也可以在tableName属性中通过使用%通配符来匹配所有数据库表,只有匹配的表才会自动生成文件
        -->
        <table tableName="user_info" enableCountByExample="true" enableUpdateByExample="true"
               enableDeleteByExample="true" enableSelectByExample="true"
               enableSelectByPrimaryKey="true">

            <!--数据库表主键 -->
            <generatedKey column="id" sqlStatement="MySql" identity="true"></generatedKey>

        </table>

        <table tableName="sys_user_role" enableCountByExample="true" enableUpdateByExample="true"
               enableDeleteByExample="true" enableSelectByExample="true"
               enableSelectByPrimaryKey="true">

            <!--数据库表主键 -->
            <generatedKey column="id" sqlStatement="MySql" identity="true"></generatedKey>

        </table>

    </context>
</generatorConfiguration>
```
#### 终端生成mapper dao domain命令
```
mvn mybatis-generator:generate
```
#### service
```
package com.example.demo.service;


import com.example.demo.domain.UserInfo;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(String uid);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(String uid);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}
```
#### service.imp
```
package com.example.demo.service.imp;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(String uid) {
        return userInfoMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(String uid) {
        return userInfoMapper.selectByPrimaryKey(uid);
    }

    @Override
    public List<UserInfo> selectAll() {
        return userInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }
}

```
#### controller
```
package com.example.demo.controller;

import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String uid){
        return userService.deleteByPrimaryKey(uid);
    }

    @RequestMapping(value = "/insert")
    public int insert(UserInfo record){
        return userService.insert(record);
    }

    @RequestMapping(value = "/selectByPrimaryKey")
    public UserInfo selectByPrimaryKey(String uid){
        return userService.selectByPrimaryKey(uid);
    }

    @RequestMapping(value = "/selectAll")
    public List<UserInfo> selectAll(){
        return userService.selectAll();
    }

    @RequestMapping(value = "/updateByPrimaryKey")
    public int updateByPrimaryKey(UserInfo record){
        return userService.updateByPrimaryKey(record);
    }
}
```
#### Application
```
package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan(basePackages = "com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}

```