package com.zzw.iCache;

import com.zzw.iCache.Entity.ProductInfo;

public interface TestFacade {
    public Object put(ProductInfo productInfo);
    public Object get(String key);
}
