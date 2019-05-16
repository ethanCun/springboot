<p><a href="https://blog.csdn.net/isea533/article/details/50412212">SpringBoot静态资源处理</a></p>

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