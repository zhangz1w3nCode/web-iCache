package com.zzw.iCache;

import com.zzw.iCache.Entity.ProductInfo;

public interface TestFacade {
    public void put(String key, ProductInfo productInfo);
    public Object get();
}
