package com.zzw.iCache.Test;

import com.zzw.iCache.Cache.Cache;
import com.zzw.iCache.CacheConstant.CacheConstant;
import com.zzw.iCache.Test.Entity.ProductInfo;
import com.zzw.iCache.annocation.iCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@Slf4j
public class TestController {

    @iCache(name = "productInfoCache")
    Cache<ProductInfo> productInfoCache;

    @PostMapping("/put")
    public Object put(@RequestParam String key,@RequestBody ProductInfo productInfo) {
        productInfoCache.put(key, productInfo);
        return "success";
    }

    @PostMapping("/get")
    public Object put(@RequestParam String key) {
        return productInfoCache.get(key);
    }

    @PostMapping("/test")
    public Object put() {
        return "6";
    }


}
