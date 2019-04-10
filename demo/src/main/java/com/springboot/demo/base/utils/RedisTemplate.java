package com.springboot.demo.base.utils;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;

public class RedisTemplate extends org.springframework.data.redis.core.RedisTemplate {

    public static ThreadLocal<Integer> indexdb = new ThreadLocal<Integer>(){

        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    //为了实现分库存储，我们必须重写自带的RedisTemplate，该类继承了springdataredis的RedisTemplate类，
    //我们加入indexdb为Redis库的编号，重写了里面的RedisConnection方法，加入是否有库值传递进来的逻辑判断。
    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection,
                                                   boolean existingConnection) {

        System.out.println("重写自带的RedisTemplate");

        try {

            Integer dbindex = indexdb.get();

            //如果设置了dbindex
            if (dbindex != null){

                if (connection instanceof JedisConnection){

                    if (((JedisConnection)connection).getNativeConnection().getDB().intValue() != dbindex){

                        connection.select(dbindex);
                    }
                }else {

                    connection.select(dbindex);
                }
            }else {
                connection.select(0);
            }
        }finally {

            indexdb.remove();
        }

        return super.preProcessConnection(connection, existingConnection);
    }
}
