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

#### @Component 与 @ComponentScan
```
1. 使用@Component或@Service或@Repository定义bean
2.使用@CompoentScan扫描指定的包

对Spring Boot项目：
1. 如果你的其他包都在使用了@SpringBootApplication注解的main
app所在的包及其下级包，则你什么都不用做，SpringBoot会自动帮你把其他包都扫描了
2.如果你有一些bean所在的包，不在main
app的包及其下级包，那么你需要手动加上@ComponentScan注解并指定那个bean所在的包

定义@CoponentScan(“com.demo”)
定义分别扫描两个包
@ComponentScan({“com.demo.springboot”,”com.demo.somethingelse”})

3.如果仅仅只写@ComponentScan({"com.demo.somethingelse"})将导致com.demo.springboot包下的类无法被扫描到（框架原始的默认扫描效果无效了）

对非Springboot项目，@ComponentScan(basePackages = {"com.demo.package1", "com.demo.package2"})相当于：
<context:component-scan base-package="com.demo.package1, com.demo.package2"/>

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

#### @ConditionalOnProperty
```

/**
 *
 * 配置Spring Boot通过@ConditionalOnProperty来控制Configuration是否生效
 *
 * 通过其两个属性name以及havingValue来实现的，
 * 其中name用来从application.properties中读取某个属性值
 * ，如果该值为空，则返回false;如果值不为空，则将该值与havingValue指定的值进行比较，
 * 如果一样则返回true;否则返回false。如果返回值为false，则该configuration不生效；为true则生效。
 * */
//@ConditionalOnProperty(name = "configure.czy.name", havingValue = "czy2")
@ConditionalOnProperty(prefix = "configure.czy", value = "name", havingValue = "czy")
@Configuration
public class TestBean {

    public TestBean(){

        System.out.println("test bean =========");
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

#### @EnableAsync @Async
```
我们在使用多线程的时候，往往需要创建Thread类，或者实现Runnable接口，如果要使用到线程池，我们还需要来创建
Executors，在使用spring中，已经给我们做了很好的支持。只要要@EnableAsync就可以使用多线程。使用@Async就可以
定义一个线程任务。通过spring给我们提供的ThreadPoolTaskExecutor就可以使用线程池。下面举个例子来说明

@EnableAsync  // 启用异步任务
@Async    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行 
```
###### 例子:
```
配置线程池:

package com.example.demo.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@ComponentScan(basePackages = "com.example.demo.Service")
@EnableAsync //启用异步任务
public class ThreadConfig extends AsyncConfigurerSupport {

    //执行需要依赖线程池，重写AsyncConfigurerSupport配置线程池
    @Override
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(12);

        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor;
    }

    // 执行需要依赖线程池，这里就来配置一个线程池
//    @Bean
//    public Executor executor(){
//
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setCorePoolSize(5);
//        threadPoolTaskExecutor.setMaxPoolSize(10);
//        threadPoolTaskExecutor.setQueueCapacity(12);
//
//        threadPoolTaskExecutor.initialize();
//
//        return threadPoolTaskExecutor;
//    }

}


Service:
@Service(value = "asyncTaskService")
public class AsyncTaskService {


    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
    @Async
    public void test1(){

        System.out.println("test1 = " + Thread.currentThread().getName() + " " + UUID.randomUUID().toString());

        try {
            Thread.sleep(new Random().nextInt(10));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Async
    public void test2(){

        System.out.println("test2 = " + Thread.currentThread().getName() + " " + UUID.randomUUID().toString());

        try {

            Thread.sleep(new Random().nextInt(10));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

Web:
   @Resource
    private AsyncTaskService asyncTaskService;

    @GetMapping(value = "/enableAsyncTest")
    public void enableAsync(){

        for (int i=0; i<10; i++){

            asyncTaskService.test1();
            asyncTaskService.test2();
        }
    }



@enaleAsync + @Async输出:
test2 = ThreadPoolTaskExecutor-6 65356a3c-52bb-470a-acd0-72406a477f4d
test2 = ThreadPoolTaskExecutor-8 359bfd62-891d-46d1-a42c-d7f3cecc78a6
test2 = ThreadPoolTaskExecutor-4 848a2026-7c91-41f6-8163-1e050faa912e
test1 = ThreadPoolTaskExecutor-5 ffa45b9f-c935-457b-a13b-2b7753356917
test1 = ThreadPoolTaskExecutor-1 80d1a550-bec8-4a13-9402-8bdd01e7493d
test2 = ThreadPoolTaskExecutor-2 3b3423bd-634f-46f7-b2d5-39f2d275e6dc
test1 = ThreadPoolTaskExecutor-3 e1984921-49c6-404d-85f7-3ae284037d13
test1 = ThreadPoolTaskExecutor-7 ae7a5200-cc45-449a-9d8a-ce94d8c4126c
test2 = ThreadPoolTaskExecutor-2 463a1e32-d591-4a51-8fad-0532bcd7165d
test1 = ThreadPoolTaskExecutor-4 6ef6a9f6-3d82-4b6a-a93f-7f5b48e43e62
test2 = ThreadPoolTaskExecutor-4 6276bb13-6e0b-40fa-a308-b5e720b6ff41
test1 = ThreadPoolTaskExecutor-7 92232a60-5b65-4614-ab0f-69c07139e11c
test2 = ThreadPoolTaskExecutor-3 5508c7a9-7cfc-4454-adbd-e9c665820713
test1 = ThreadPoolTaskExecutor-8 a4d974b2-161e-41e2-a23b-7aea3b3abbc3
test2 = ThreadPoolTaskExecutor-2 1218f8ad-7b7b-4d61-a192-9ea4ab27730a
test1 = ThreadPoolTaskExecutor-8 8f7dc67b-0b88-4c92-a9dd-7fb0bddb0a6e
test2 = ThreadPoolTaskExecutor-1 d338e198-121b-484c-b63f-08529affa43f
....

不开启输出, 不会创建多个线程:http-nio-8080-exec-1
test1 = http-nio-8080-exec-1 8371a21a-5cb3-41b8-a28e-36e783f52b51
test2 = http-nio-8080-exec-1 299fd863-94f3-4326-9c1e-35280f8e38cc
test1 = http-nio-8080-exec-1 d12be40a-75c7-4414-887a-5db9b3f3abe4
test2 = http-nio-8080-exec-1 d4696aad-b7ec-4f85-9c09-84291bccf542

```

#### @AliasFor
```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AliasFor {

	@AliasFor("attribute")
	String value() default "";

	@AliasFor("value")
	String attribute() default "";

	Class<? extends Annotation> annotation() default Annotation.class;
}

1. 在同一个注解内，对两个不同的属性一起使用，互为别名，比如@RequestMapping中path和value成对使用，互为别名。

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestMapping {
    String name() default "";

    @AliasFor("path") // 此时path和value值是一样的，如果不一样会报错
    String[] value() default {};

    @AliasFor("value") // 此时path和value值是一样的，如果不一样会报错
    String[] path() default {};
    ... 
}


2. 为其它注解别名
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface czyconfigure {

    //attribute和value都指定时， 两者必须一致
    @AliasFor(value="value", annotation = Configuration.class)
    String value() default "";
}

```

#### @Value
##### <p><a href="https://www.cnblogs.com/duanxz/p/4520627.html">参考链接</a></p>

#### TODO: @EnableScheduling, @EnableTransactionManagement, @EnableAspectJAutoProxy, @EnableWebMvc, @PostConstruct, @service static类注入失败