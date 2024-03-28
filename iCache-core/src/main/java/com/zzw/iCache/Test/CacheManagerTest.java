package com.zzw.iCache.Test;

import com.zzw.iCache.Cache.Cache;
import com.zzw.iCache.CacheConfig.CacheConfig;
import com.zzw.iCache.CacheManager.CacheManager;
import com.zzw.iCache.CacheManager.implement.CacheManagerImp;
import com.zzw.iCache.Entity.ProductInfo;

import java.util.ArrayList;

/**
 * @Author: zhangyang
 * @Data:2024/3/27 19:23
 * @Description:
 */
public class CacheManagerTest {
    public static void main(String[] args) {

        CacheManager cacheManager = new CacheManagerImp();
        CacheConfig cacheConfig = new CacheConfig("测试缓存名称", "CAFFEINE", 11, 22, 10, 20);

        Cache<ProductInfo> cache = cacheManager.createCache(cacheConfig, new ArrayList<>(), new ArrayList<>());
        cache.put("key1",new ProductInfo("1" , "测试商品1", 20));

        System.out.println(cache.get("key1"));
        System.out.println(cache.get("key2"));
    }
}
