package com.zzw.iCache.RealCache;

import com.zzw.iCache.RealCache.valueWrapper.ValueWrapper;

/**
 * 真正缓存的接口
 * @param <V>
 */
public interface RealCache<V>{

    ValueWrapper<V> get(String key);

    void put(String key, V value);

}