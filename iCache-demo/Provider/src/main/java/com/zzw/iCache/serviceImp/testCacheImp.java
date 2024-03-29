package com.zzw.iCache.serviceImp;


import com.zzw.iCache.Cache.Cache;
import com.zzw.iCache.Entity.ProductInfo;
import com.zzw.iCache.TestFacade;
import com.zzw.iCache.annocation.iCache;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0")
public class testCacheImp implements TestFacade {

    @iCache(name = "productInfoCache")
    Cache<ProductInfo> productInfoCache;

    @Override
    public void put(String key, ProductInfo productInfo) {
        productInfoCache.put(key, productInfo);
    }

    @Override
    public Object get() {
        return "6666";
    }
}
