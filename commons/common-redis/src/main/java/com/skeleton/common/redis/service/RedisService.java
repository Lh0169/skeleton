package com.skeleton.common.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) { redisTemplate.opsForValue().set(key, value); }

    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) return null;
        return (T) value;
    }

    public void delete(String key) { redisTemplate.delete(key); }

    public boolean hasKey(String key) { return Boolean.TRUE.equals(redisTemplate.hasKey(key)); }

    public long getExpire(String key) {
        Long expire = redisTemplate.getExpire(key);
        return expire != null ? expire : -1L;
    }
}
