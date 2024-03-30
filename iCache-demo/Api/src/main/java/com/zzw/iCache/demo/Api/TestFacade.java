package com.zzw.iCache.demo.Api;

import com.zzw.iCache.demo.Api.Entity.ProductInfo;
import com.zzw.iCache.RealCache.valueWrapper.ValueWrapper;

import java.util.List;

public interface TestFacade {
    public Object put(ProductInfo productInfo);
    public Object get(String key);

    public List<ValueWrapper<ProductInfo>> getAllValues();
}
