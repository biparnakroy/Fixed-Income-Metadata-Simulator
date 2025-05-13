
package com.example.service;

import com.example.model.Security;
import com.example.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    private SecurityRepository repository;

    @Autowired
    private RedisTemplate<String, Security> redisTemplate;

    private final String CACHE_PREFIX = "SECURITY:";

    public Security getSecurity(String id) {
        Security cached = (Security) redisTemplate.opsForValue().get(CACHE_PREFIX + id);
        if (cached != null) return cached;

        Security sec = repository.findById(id).orElse(null);
        if (sec != null) redisTemplate.opsForValue().set(CACHE_PREFIX + id, sec);
        return sec;
    }

    public Security saveSecurity(Security sec) {
        redisTemplate.delete(CACHE_PREFIX + sec.getId());
        return repository.save(sec);
    }
}
