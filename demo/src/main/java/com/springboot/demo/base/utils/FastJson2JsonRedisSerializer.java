package com.springboot.demo.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;
import sun.plugin2.message.Serializer;

import java.nio.charset.Charset;

/*
  *  自定义序列化
  *
  * @author ethan
  * @date 2019/4/9 7:35 PM
  * @param
  * @return
  */

//@Lazy:没加注解之前只要容器启动就会实例化bean， 而加上@Lazy注解则必须在第一次调用的时候才会加载
//@Lazy
//@Component
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public FastJson2JsonRedisSerializer(Class<T> clazz){

        super();

        this.clazz = clazz;
    }


    @Override
    public byte[] serialize(T t) throws SerializationException {

        if (t == null){
            return new byte[0];
        }

        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {

        if (bytes == null || bytes.length <= 0){

            return null;
        }

        String str = new String(bytes, DEFAULT_CHARSET);

        return (T)JSON.parseObject(str, clazz);
    }
}
