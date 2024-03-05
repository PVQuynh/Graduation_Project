package com.example.UserService.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

@Component
@EnableCaching
public class MyCache {
    private final Cache cache;

    public MyCache(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("myCache");
    }

    public void saveToCache(String key, Object value) {
        cache.put(key, value);
    }

    public Object getFromCache(String key) {
        Cache.ValueWrapper wrapper = cache.get(key);
        return (wrapper != null) ? wrapper.get() : null;
    }
    public void deleteFromCache(String key) {
        cache.evict(key);
    }

    public void updateCache(String key, Object value) {
        if (cache.get(key) != null) {
            cache.put(key, value);
        }
    }
}
