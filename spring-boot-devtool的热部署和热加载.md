#### pom.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.4.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
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

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```
#### application.properties
```
#热部署生效
spring.devtools.restart.enabled: true
#设置重启的目录
spring.devtools.restart.additional-paths: src/main/java
#classpath目录下的WEB-INF文件夹内容修改不重启
#spring.devtools.restart.exclude: WEB-INF/**
#关闭thymeleaf的缓存 以便spring-boot-devtools能够实时热加载网页
spring.thymeleaf.cache=false
```
#### main
```
/**
 * 开启热部署需要在idea中设置自动编译
 * idea设置自动编译参考链接：
 * https://jingyan.baidu.com/article/ca2d939d5b0c05eb6d31ce77.html
 * */

/**
 *
 * 修改类–>保存：应用会重启
 *     修改配置文件–>保存：应用会重启
 *     修改页面–>保存：应用不会重启，但会重新加载，
 *     页面会刷新（原理是将spring.thymeleaf.cache设为false，参考:Spring Boot配置模板引擎）
 * */

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}
```
#### 测试热部署
```
/**
 * 开启热部署需要在idea中设置自动编译
 * idea设置自动编译参考链接：
 * https://jingyan.baidu.com/article/ca2d939d5b0c05eb6d31ce77.html
 * */

/**
 *
 * 修改类–>保存：应用会重启
 *     修改配置文件–>保存：应用会重启
 *     修改页面–>保存：应用不会重启，但会重新加载，
 *     页面会刷新（原理是将spring.thymeleaf.cache设为false，参考:Spring Boot配置模板引擎）
 * */

@Configuration
public class testDevTools {

    @Bean
    public void test(){

        System.out.println("111111");
    }

    public testDevTools(){

        System.out.println("222222");
    }
}
```
#### 测试热加载
```
<h1>spring-boot-devtools</h1>
<h2>1111</h2>

<pre>

    修改类–>保存：应用会重启
    修改配置文件–>保存：应用会重启
    修改页面–>保存：应用不会重启，但会重新加载，页面会刷新（原理是将spring.thymeleaf.cache设为false，参考:Spring Boot配置模板引擎）
</pre>

</body>
</html>
```
#### 配置
```
IDEA配置

当我们修改了Java类后，IDEA默认是不自动编译的，而spring-boot-devtools又是监测classpath下的文件发生变化才会重启应用，所以需要设置IDEA的自动编译：

（1）File-Settings-Compiler-Build Project automatically



（2）ctrl + shift + alt + /,选择Registry,勾上 Compiler autoMake allow when app running



测试

修改类–>保存：应用会重启
修改配置文件–>保存：应用会重启
修改页面–>保存：应用不会重启，但会重新加载，页面会刷新（原理是将spring.thymeleaf.cache设为false，参考:Spring Boot配置模板引擎）
```