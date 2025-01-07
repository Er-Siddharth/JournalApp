package com.AsiaAutmation.JournalApp.Service;

import org.junit.jupiter.api.Assertions;
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

    @Test
    public void checkRedis(){
        Assertions.assertDoesNotThrow(()->{
            redisTemplate.opsForValue().set("email","demoEmail@gmail.com");
            Object email = redisTemplate.opsForValue().get("email");
            System.out.println(email.toString());
        });
    }
}
