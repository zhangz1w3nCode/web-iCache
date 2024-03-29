package com.zzw.iCache.Cache;

/**
 * 统一缓存接口
 * @param <V>
 */
public interface Cache<V>{
    V get(String key);

    void put(String key, V value);
}
