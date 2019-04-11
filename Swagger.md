#### Swagger@文档地址 # Swagger的port和address与application.yml中的一致
#### 例子: http://localhost:8080/swagger-ui.html
<p><a href="https://blog.csdn.net/u014231523/article/details/76522486">Swagger注解详细说明</a></p>

```
@Configuration
@EnableSwagger2 //开启Swigger2
public class SwaggerConfig {

    public Docket swaggerSpringMvcPlugin(){

        return new Docket(DocumentationType.SWAGGER_2).select().
                apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }
}

```
```

@RestController
@ResponseBody
@RequestMapping("/user")
@Api(value = "UserController相关的API", tags = {"用户操作相关的接口"}, description = "用户信息描述字段：description")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询", notes = "获取所有的用户信息")
    @GetMapping("/fetchAllUser")
    public List<User> fetchAllUser(){

        return userService.fetchAllUser();
    }

    @ApiOperation(value = "查询", notes = "根据id查找用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "Integer")
    })
    @GetMapping("/getUserWithId")
    public User getUserWithId(int id){

        return userService.getUserWithId(id);
    }

    @ApiOperation(value = "新增", notes = "新增一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "age", value = "用户年龄", required = false, dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "updateDate", value = "更新时间", required = false, dataType = "String")
    })
    @PostMapping("/insertUser")
    public int insertUser(User user){

        return userService.insertUser(user);
    }

    @ApiOperation(value = "删除", notes = "根据id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "Integer")
    @PostMapping("/deleteUserWithId")
    public int deleteUserWithId(int id){

        return userService.deleteUserWithId(id);
    }

    @ApiOperation(value = "更新", notes = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "age", value = "用户年龄", required = false, dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "updateDate", value = "更新时间", required = false, dataType = "String")
    })
    @PostMapping("/updateUser")
    public int updateUser(User user){

        return userService.updateUser(user);
    }
}
```
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
    <groupId>com.swigger2</groupId>
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
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.1</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <!--添加druid和fastjson依赖，使用阿里巴巴druid连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.28</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.30</version>
        </dependency>

        <!--Swagger2-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>

        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <!--热部署-->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork> <!-- 如果没有该配置，devtools不会生效 -->
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```