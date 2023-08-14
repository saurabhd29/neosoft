package com.service.otp.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class OtpRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    public void put(String key, String otp){
        try{
            redisTemplate.opsForValue().set(key,otp);
            log.info("Key Value :: {} {}",key,otp);
            redisTemplate.expire(key,60, TimeUnit.SECONDS);
        }
        catch (Exception e) {

        }
    }

    public Optional<Object> get(String key){
        try {
            if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
                return Optional.ofNullable(redisTemplate.opsForValue().get(key));
            } else {
                return Optional.empty();
            }
        }catch (Exception e){

        }
        return null;
    }

    public void remove(String key){
        try {
            redisTemplate.delete(key);
        }
        catch (Exception e){

        }
    }



}
