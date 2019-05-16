<p><a href="https://blog.csdn.net/isea533/article/details/50412212">SpringBoot静态资源处理</a></p>

#### @EnableAutoConfiguration: 让spring根据结构布局自动加载各个类的注解 并进行调用
```
spring通常建议我们将main方法所在的类放到一个root包下，@EnableAutoConfiguration（开启自动配置）注解通常都

放到main所在类的上面，下面是一个典型的结构布局：

com
 +- example
     +- myproject
         +- Application.java
         |
         +- domain
         |   +- Customer.java
         |   +- CustomerRepository.java
         |
         +- service
         |   +- CustomerService.java
         |
         +- web
             +- CustomerController.java

这样@EnableAutoConfiguration可以从逐层的往下搜索各个加注解的类，例如，你正在编写一个JPA程序（如果你的pom里进行了配置的话），spring会自动去搜索加了@Entity注解的类，并进行调用

```

#### @SpringbootApplication
```
使用@SpringbootApplication注解  可以解决根类或者配置类（我自己的说法，就是main所在类）头上注解过多的问题，

一个@SpringbootApplication相当于@Configuration,@EnableAutoConfiguration和 @ComponentScan 并具

有他们的默认属性值

```

#### @Configuration
```

1. @Configuration配置spring并启动spring容器

@Configuration
public class TestConfiguration {
    public TestConfiguration() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }
}

2. @Configuration启动容器+@Bean注册Bean，@Bean下管理bean的生命周期
@Configuration
public class TestConfiguration {
    public TestConfiguration() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }

    // @Bean注解注册bean,同时可以指定初始化和销毁方法
    // @Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")
    @Bean
    @Scope("prototype")
    public TestBean testBean() {
        return new TestBean();
    }
}

3. @Configuration启动容器+@Component注册Bean
//添加注册bean的注解
@Component
public class TestBean {

    private String username;

    set get
}

@Configuration
//添加自动扫描注解，basePackages为TestBean包路径
@ComponentScan(basePackages = "com.dxz.demo.configuration")
public class TestConfiguration {
    public TestConfiguration() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }

    /*// @Bean注解注册bean,同时可以指定初始化和销毁方法
    // @Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
    @Bean
    @Scope("prototype")
    public TestBean testBean() {
        return new TestBean();
    }*/
}
```


#### @EnableConfigurationProperties({PropertiesConfig.class})
```
@EnableConfigurationProperties注解的作用是：使使用 @ConfigurationProperties 注解的类生效。

如果一个配置类只配置@ConfigurationProperties注解，而没有使用@Component，那么在IOC容器中是获取不到

properties 配置文件转化的bean。说白了 @EnableConfigurationProperties 相当于把使用  

@ConfigurationProperties 的类进行了一次注入。


```
#### @ConfigurationProperties(prefix="configure.czy")

```
有时候有这样子的情景，我们想把配置文件的信息，读取并自动封装成实体类，这样子，我们在代码里面使用就轻松方便多了，这

时候，我们就可以使用@ConfigurationProperties，它可以把同类的配置信息自动封装成实体类


configure.czy: 为application.yml的前缀


```
#### 实例
```
DemoApplicatiom:

@SpringBootApplication
//@EnableConfigurationProperties用于使使用 @ConfigurationProperties 注解的类生效。
//在使用 @ConfigurationProperties 注解的类上加上@component可以达到同样的效果
//@EnableConfigurationProperties({Config.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

PropertiesConfig:

package com.example.demo.Config;

import com.example.demo.entity.Animal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "configure.czy")
public class Config {

    private float offset = 0;
    private int age = 10;
    private String name = "";

    Animal animal = new Animal();

    Set get	
}

Web:

@RestController
public class TestController {

    @Autowired
    private Config config;

    @GetMapping(value = "/config")
    public Object config(){

        return config.getAnimal().toString() + config.getOffset() + config.getAge() + config.getName();
    }
}

Application.yml:

configure:
  czy:
   offset: 11
   age: 10
   name: czy
   animal:
     name: ccccccc

```

#### TODO:@EnableAsync, @EnableScheduling, @EnableTransactionManagement, @EnableAspectJAutoProxy, @EnableWebMvc。