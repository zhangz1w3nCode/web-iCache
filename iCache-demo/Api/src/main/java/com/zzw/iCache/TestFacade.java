package com.zzw.iCache;

import com.zzw.iCache.Entity.ProductInfo;
import com.zzw.iCache.RealCache.valueWrapper.ValueWrapper;
import org.springframework.cache.Cache;

import java.util.List;

public interface TestFacade {
    public Object put(ProductInfo productInfo);
    public Object get(String key);

    public List<ValueWrapper<ProductInfo>> getAllValues();
}
