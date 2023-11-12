package com.example.kafkaconsumer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveDataToRedis(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getDataFromRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
