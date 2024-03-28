package com.zzw.iCache.Test;

import com.zzw.iCache.Cache.Cache;
import com.zzw.iCache.Entity.ProductInfo;
import com.zzw.iCache.annocation.iCache;
import org.springframework.stereotype.Component;

@Component
public class ProductInfoHolder {

    @iCache("productCache")
    Cache<ProductInfo> productInfoCache;

    void put(String skuSn, ProductInfo productInfo) {
        productInfoCache.put(skuSn, productInfo);
    }

    ProductInfo get(String skuSn) {
        return productInfoCache.get(skuSn);
    }
}
