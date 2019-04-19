package com.example.demo.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.demo.entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
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
