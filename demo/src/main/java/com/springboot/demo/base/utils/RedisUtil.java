package com.springboot.demo.base.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Lazy
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //指定缓存失效时间
    public boolean expire(String key, long time){

        try{

            if (time > 0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }

            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //获取过期时间
    public long getExpireTime(String key){

        return redisTemplate.getExpire(key);
    }

    //判断key是否存在
    public boolean hasKey(String key){

        try {

            return redisTemplate.hasKey(key);
        }catch (Exception e){

            e.printStackTrace();

            return false;
        }
    }

    //删除缓存
    public void del(String ... key){

        if (key != null && key.length > 0){

            if (key.length == 1){

                redisTemplate.delete(key);
            }else {

                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //切换indexdb
    public void changeIndexDb(int indexdb) {

        JedisConnectionFactory factory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();

        factory.setDatabase(indexdb);

        redisTemplate.setConnectionFactory(factory);
    }

    //============================String=============================
    //普通缓存取
    public Object get(String key, int indexdb){

        this.changeIndexDb(indexdb);

        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    //普通缓存存
    public boolean set(String key, Object value, int indexdb){

        try {

            this.changeIndexDb(indexdb);

            redisTemplate.opsForValue().set(key, value);

            return true;
        }catch (Exception e){

            e.printStackTrace();

            return false;
        }
    }

    //普通缓存放入 并设置时间
    public boolean set(String key, Object value, long time){

        try {

            if (time > 0){

                redisTemplate.opsForValue().set(key, value, time);
            }else {

                redisTemplate.opsForValue().set(key, value);
            }

            return true;
        }catch (Exception e){

            e.printStackTrace();

            return false;
        }
    }

    //递增
    public long incr(String key, long delta){

        if (delta < 0){

            throw new RuntimeException("递增因子必须大于0");
        }

        return redisTemplate.opsForValue().increment(key, delta);
    }

    //递减
    public long decr(String key, long delta){

        if (delta < 0){

            throw new RuntimeException("递减因子必须大于0");

        }

        return redisTemplate.opsForValue().decrement(key, delta);
    }

    //================================Map=================================
    //hashGet key:键 item:项
    public Object hget(String key, String item){

        return redisTemplate.opsForHash().get(key, item);
    }

    //获取hashKey对应的所有键值
    public Map<Object, Object> hmget(String key){

        return redisTemplate.opsForHash().entries(key);
    }

    //hashSet
    public boolean hmset(String key, HashMap<String, Object> map){

        try {

            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //HashSet 并设置时间
    public boolean hmset(String key, HashMap<String, Object> map, long time){

        try {

            redisTemplate.opsForHash().putAll(key, map);

            if (time > 0){

                expire(key, time);
            }

            return true;

        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //向一张hash表中放入数据,如果不存在将创建  键 项 值
    public boolean hset(String key, String item, Object value){

        try {

            redisTemplate.opsForHash().put(key, item, value);
            return true;

        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //向一张hash表中放入数据,如果不存在将创建, 设置时间
    public boolean hset(String key, String item, Object value, long time){

        try {

            redisTemplate.opsForHash().put(key, item, value);

            if (time > 0){

                expire(key, time);
            }

            return true;

        }catch (Exception e){

            e.printStackTrace();

            return false;
        }
    }

    //删除hash表中的值
    public void del(String key, Object ... item){

        redisTemplate.opsForHash().delete(key, item);
    }

    //判断hash表中是否有该项的值
    public boolean hhaskey(String key, String item){

        return redisTemplate.opsForHash().hasKey(key, item);
    }

    //hash递增 如果不存在,就会创建一个 并把新增后的值返回
    public double hincr(String key, String item, double by){

        return redisTemplate.opsForHash().increment(key, item, by);
    }

    //hash递减
    public double dhecr(String key, String item, double by){

        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //============================set=============================
    //sget
    public Set<Object> sGet(String key){

        try {

            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){

            e.printStackTrace();

            return null;
        }
    }

    //根据value从一个set中查询,是否存在
    public boolean sHahKey(String key, Object value){

        try {

            return redisTemplate.opsForSet().isMember(key, value);
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //将数据放入set缓存 返回成功个数
    public long sSet(String key, Object ... items){

        try {

            return redisTemplate.opsForSet().add(key, items);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    //将数据放入set缓存 返回成功个数 设置时间
    public long sSet(String key, long time, Object ... items){

        try {

            long count = redisTemplate.opsForSet().add(key, items);

            if (count > 0){
                expire(key, time);
            }

            return count;
        }catch (Exception e){

            e.printStackTrace();
            return 0;
        }
    }

    //获取set缓存的长度
    public long sGetSize(String key){

        try {

            return redisTemplate.opsForSet().size(key);
        }catch (Exception e){

            e.printStackTrace();
            return 0;
        }
    }

    //移除 返回成功移除的个数
    public Long setRemove(String key, Object ... values){

        try {

            Long count = redisTemplate.opsForSet().remove(key, values);

            return count;
        }catch (Exception e){

            e.printStackTrace();

            return null;
        }
    }

    //===============================list=================================
    //获取list缓存的内容
    public List<Object> lGet(String key, long start, long end){

        try {


            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){

            e.printStackTrace();
            return null;
        }
    }

    //获取list缓存的长度
    public long lGetListSize(String key){

        try {
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){

            e.printStackTrace();
            return 0;
        }
    }

    //通过索引 获取list中的值
    //index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
    public Object lGetIndex(String key, int index){

        try {

            return redisTemplate.opsForList().index(key, index);
        }catch (Exception e){

            return null;
        }
    }

    //将list放入缓存
    public boolean lSet(String key, Object value){

        try {

            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //将list放入缓存
    public boolean lSet(String key, Object value, long time){

        try {

            redisTemplate.opsForList().rightPush(key, value);

            if (time > 0){

                expire(key, time);
            }
            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //将list全部放入缓存
    public boolean lSet(String key, List<Object> value){

        try {

            redisTemplate.opsForList().rightPushAll(key, value);

            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //将list全部放入缓存
    public boolean lSet(String key, List<Object> value, long time){

        try {

            redisTemplate.opsForList().rightPushAll(key, value);

            if (time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //根据索引修改list中的某条数据 key 键 index 索引 value 值
    public boolean lUpdateIndex(String key, long index, Object value){

        try {

            redisTemplate.opsForList().set(key, index, value);
            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    //移除N个值为value key 键  count 移除多少个 value 值 return移除的个数
    public Long lRemove(String key, long count, Object value){

        try {

            return redisTemplate.opsForList().remove(key, count, value);
        }catch (Exception e){

            e.printStackTrace();
            return null;
        }
    }

    //测试
//    public static void main(String[] args){
//
//        JedisPool jedisPool = new JedisPool(null, "localhost", 6379, 100, "czy1212");
//        Jedis jedis = jedisPool.getResource();
//        jedis.select()
//        Set<String> set = jedis.keys("*");
//        for (String s : set){
//
//            System.out.println("s = " + s);
//        }
//    }
}
