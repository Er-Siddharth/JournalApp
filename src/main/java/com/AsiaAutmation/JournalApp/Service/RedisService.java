package com.AsiaAutmation.JournalApp.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass){
        Object o = redisTemplate.opsForValue().get("Weather_of_"+key);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if(o==null) return null;
            return objectMapper.readValue(o.toString(),entityClass);
        } catch (Exception e) {
            log.error("Exception Occured : "+e);
            return null;
        }
    }

    public void set(String key, Object weatherObj,Long ttl){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String strObj = objectMapper.writeValueAsString(weatherObj);
            redisTemplate.opsForValue().set("Weather_of_"+key,strObj,ttl, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            log.error("Exception Occured : "+e);
        }


    }
}
