package com.sky.test;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
public class SpingDataRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testRedisTemplate(){
        System.out.println(redisTemplate);
        ValueOperations valueOperations=redisTemplate.opsForValue();
        HashOperations hashOperations=redisTemplate.opsForHash();
        ListOperations listOperations=redisTemplate.opsForList();
        SetOperations setOperations=redisTemplate.opsForSet();
        ZSetOperations zSetOperations=redisTemplate.opsForZSet();
    }


    /*
    *
    * 操作字符串类型的数据
    * */
    @Test
    public void testString(){
        //set get setex setnx
        redisTemplate.opsForValue().set("city","重庆");
        String city=(String) redisTemplate.opsForValue().get("city");
        System.out.println(city);


        redisTemplate.opsForValue().set("code","1234",3, TimeUnit.MINUTES);

        redisTemplate.opsForValue().setIfAbsent("lock","1");
        redisTemplate.opsForValue().setIfAbsent("lock","2");

    }



    /*
     *
     * 操作哈希类型的数据
     * */

    @Test
    public void testHash(){
        //hset hget hdel hksys hvals
        HashOperations hashOperations =redisTemplate.opsForHash();

        hashOperations.put("101","name","Tony");
        hashOperations.put("101","age","20");

        String name = (String) hashOperations.get("101", "name");
        System.out.println(name);

        Set keys = hashOperations.keys("100");
        System.out.println(keys);

        List values = hashOperations.values("100");
        System.out.println(values);

        hashOperations.delete("101", "age");
    }
}
