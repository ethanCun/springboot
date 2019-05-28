#### pom引入包和mybatis-generator-maven-plugin
```
 <dependencies>
	
	<!-- @Entity @Table @Column -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

	<!-- 连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.0</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
	
	<!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.1</version>
        </dependency>


        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!-- 通用 mapper -->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>1.1.7</version>
        </dependency>

        <!--mapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper</artifactId>
            <version>4.1.4</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
            <version>8.0.15</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <!--mybatis generator-->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.7</version>
                <configuration>
                    <configurationFile>
	src/main/resources/mybatisConfg/mybatisGenerator.xml</configurationFile>
                    <!--允许覆盖-->
                    <overwrite>true</overwrite>
                    <!--允许移动-->
                    <verbose>true</verbose>
                </configuration>
                <dependencies>

                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <scope>runtime</scope>
                        <version>8.0.15</version>
                    </dependency>

                    <dependency>
                        <groupId>com.alibaba</groupId>
                        <artifactId>druid</artifactId>
                        <version>1.1.0</version>
                    </dependency>

                    <!-- 通用 mapper -->
                    <dependency>
                        <groupId>tk.mybatis</groupId>
                        <artifactId>mapper-spring-boot-starter</artifactId>
                        <version>1.1.7</version>
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
    username: root
    password: czy1212
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test
    type: com.alibaba.druid.pool.DruidDataSource

mybatis:
  mapper-locations: classpath:mappers/*.xml
  type-aliases-package: com.example.demo.domain
  configuration:
    map-underscore-to-camel-case: true
```

#### 定义一个集成与mapper的父类接口
#### 注意这个包不能和业务中的dao层放到一个包下
```
//Spring-boot启动类，@MapperScan仅扫描业务接口包，不能扫描本地通用Mapper接口包，
//否则报java.lang.ClassCastException: sun.reflect.generics.reflectiveObjects.TypeVariableImpl
//cannot be cast to java.lang.Class异常
package com.example.demo.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface IMapper<T> extends MySqlMapper<T>, Mapper<T> {
}
```

#### mybatis-generator 部分字段定义 mybatisGeneratorInit.properties
```
project=src/main/java
resources=src/main/resources

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


<!--终端生成mapper dao entity 的命令：mvn mybatis-generator:generate-->
<generatorConfiguration>

    <properties resource="mybatisConfg/mybatisGeneratorInit.properties"></properties>
    <context id="Mysql" defaultModelType="flat" targetRuntime="MyBatis3Simple">

        <property name="beginningDelimiter" value="`"></property>
        <property name="endingDelimiter" value="`"></property>
        
        <plugin type="tk.mybatis.mapper.generator.MapperPlugin">

            <!--自定义通用Mapper接口，将来所有生成的mapper接口都会继承这个接口-->
            <property name="mappers" value="com.example.demo.dao.IMapper"></property>
        </plugin>
        
        <jdbcConnection driverClass="${jdbc_driver}" connectionURL="${jdbc_url}"
                        userId="${jdbc_username}" password="${jdbc_password}"></jdbcConnection>

        <javaModelGenerator targetPackage="com.example.demo.domain" targetProject="${project}">
            <property name="enableSubPackages" value="false"></property>
            <property name="trimStrings" value="true"></property>
        </javaModelGenerator>
        
        <sqlMapGenerator targetPackage="mappers" targetProject="${resources}">
            <property name="enableSubPackages" value="false"></property>
        </sqlMapGenerator>

        <javaClientGenerator type="XMLMAPPER" targetPackage="com.example.demo.dao" targetProject="${project}">
            <property name="enableSubPackages" value="false"></property>
        </javaClientGenerator>

        <table tableName="user_info" enableSelectByPrimaryKey="true" enableSelectByExample="true"
               enableDeleteByExample="true" enableUpdateByExample="true"
               enableCountByExample="true" enableDeleteByPrimaryKey="true"
               enableInsert="true" enableUpdateByPrimaryKey="true">
            <generatedKey column="uid" sqlStatement="Mysql" identity="true"></generatedKey>
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
public interface UserService {

    List<UserInfo> getAllUserInfos();

    UserInfo getUserInfoWithId(int id);

    int deleteUserInfoWithId(int id);
}
```
#### service.imp
```
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getAllUserInfos() {

        return userInfoMapper.selectAll();
    }

    @Override
    public UserInfo getUserInfoWithId(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteUserInfoWithId(int id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }
}
```
#### controller
```
package com.example.demo.controller;

import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home")
    public String home(){

        return "home";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllUserInfos")
    public List<UserInfo> getAllUserInfos(){

        return userService.getAllUserInfos();
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfoWithId")
    public UserInfo getUserInfoWithId(int id){

        return userService.getUserInfoWithId(id);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUserInfoWithId")
    public int deleteUserInfoWithId(int id){

        return userService.deleteUserInfoWithId(id);
    }
}
```
#### Application
```
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication

//注意：
//使用通用mapper:
//Spring-boot启动类，@MapperScan仅扫描业务接口包，不能扫描本地通用Mapper接口包，
//否则报java.lang.ClassCastException: sun.reflect.generics.reflectiveObjects.TypeVariableImpl
//cannot be cast to java.lang.Class异常

//所以此处不能将IMapper放到dao层下面
@MapperScan(basePackages = "com.example.demo.dao")

public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}

```