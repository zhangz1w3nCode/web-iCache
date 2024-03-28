package com.zzw.iCache.CacheFactory.implement;

import com.zzw.iCache.CacheConfig.CacheConfig;
import com.zzw.iCache.CacheConstant.CacheConstant;
import com.zzw.iCache.CacheFactory.CacheFactory;
import com.zzw.iCache.RealCache.RealCache;
import com.zzw.iCache.RealCache.implement.CaffeineCache;

/**
 * @Author: zhangyang
 * @Data:2024/3/27 16:31
 * @Description:CaffeineCacheFactory是实现类
 */
public class CaffeineCacheFactory implements CacheFactory, CacheConstant {
    @Override
    public boolean support(String cacheType) {
        return CACHE_TYPE_CAFFEINE.equals(cacheType);
    }

    @Override
    public RealCache getCache(CacheConfig cacheConfig) {
        return new CaffeineCache(cacheConfig);
    }
}
