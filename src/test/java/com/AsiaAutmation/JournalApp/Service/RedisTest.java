package com.AsiaAutmation.JournalApp.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Disabled
    @Test
    public void checkRedis(){
        Assertions.assertDoesNotThrow(()->{
            redisTemplate.opsForValue().set("email","demoEmail@gmail.com");
//            Object email = redisTemplate.opsForValue().get("email");
//            Object demo = redisTemplate.opsForValue().get("demoValue");
//            System.out.println(email.toString());
//            System.out.println(demo.toString());
        });
    }

    @Disabled
    @Test
    public void checkValues(){
        Object email = redisTemplate.opsForValue().get("email");
        Assertions.assertEquals("demoEmail@gmail.com", email.toString());
        Assertions.assertEquals("Siddharth", redisTemplate.opsForValue().get("demoValue").toString());
    }
}
