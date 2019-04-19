#### pom.xml
```
    <dependencies>

        <!--fastjson-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.56</version>
        </dependency>

        <!--简化java pojo-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.6</version>
        </dependency>

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
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
    </dependencies>
```

#### 接下来通过实现WebMvcConfigurer接口来配置FastJsonHttpMessageConverter
```
package com.example.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 *  *
 *  * 接下来通过实现WebMvcConfigurer接口来配置FastJsonHttpMessageConverter，springboot2.0版本以后推荐使用这种方式
 *  * 来进行web配置，这样不会覆盖掉springboot的一些默认配置。配置类如下：
 * */
@Configuration
public class MyWebmvcConfiguration implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        /**
         * fastJson配置实体调用setSerializerFeatures方法可以配置多个过滤方式，常用的如下：
         *
         * 　　1、WriteNullListAsEmpty  ： List字段如果为null,输出为[],而非null
         * 　　2、WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
         * 　　3、DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，
         *       默认为false（如果不配置有可能会进入死循环）
         * 　　4、WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
         * 　　5、WriteMapNullValue：是否输出值为null的字段,默认为false。
         * */
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //将字符串null转换为"" [{"address":"changsha","date":"2019-13-19 00:13:05","name":""}]
        //fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
        //pretty format [ { "address":"changsha", "date":"2019-14-19 00:14:00" } ]
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //日期格式化
        fastJsonConfig.setDateFormat("yyyy-mm-dd HH:mm:ss");

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastJsonHttpMessageConverter);
    }


}
```
#### controller
```
package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 在springboot项目里当我们在控制器类上加上@RestController注解或者其内的方法上加入@ResponseBody注解后，
 * 默认会使用jackson插件来返回json数据，下面我们利用fastjson为我们提供的FastJsonHttpMessageConverter来返回json数据。
 * */

//更多关于FastJson的使用：https://segmentfault.com/a/1190000011212806

@Controller
public class UserController {

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String home(){

        return "home";
    }

    @ResponseBody
    @GetMapping(value = "/get")
    public Object getList(){

        List<UserEntity> list = new ArrayList<>();
        UserEntity userEntity = new  UserEntity(null, "changsha", new Date());

        list.add(userEntity);

        return list;
    }

    @ResponseBody
    @GetMapping(value = "/testFastJson")
    public void testFastJson(){

        //object -> str
        UserEntity userEntity = new UserEntity("zhangsan", "hunan", new Date());

        //date会转化为时间戳
        String s1 = JSONObject.toJSONString(userEntity);

        System.out.println("s1 = " + s1);

        //str -> JSONObject
        JSONObject jsonObject = JSONObject.parseObject(s1);

        System.out.println(jsonObject.getString("name"));
        System.out.println(jsonObject.getString("address"));
        System.out.println(jsonObject.getDate("date"));

        //str -> object
        UserEntity userEntity1 = JSONObject.parseObject(s1, UserEntity.class);
        System.out.println("user1 = " + userEntity1);
    }
}
```
#### entity
```
package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserEntity {

    private String name;
    private String address;
    private Date date;

}
```