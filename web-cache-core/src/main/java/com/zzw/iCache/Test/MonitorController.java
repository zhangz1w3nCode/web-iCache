package com.zzw.iCache.Test;

import com.zzw.iCache.demo.Api.Entity.ProductInfo;
import com.zzw.iCache.demo.Api.TestFacade;
import com.zzw.iCache.monitor.CacheMonitor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@Slf4j
public class MonitorController {

    @DubboReference(version = "1.0.0", timeout = 5000)
    private CacheMonitor cacheMonitor;

    @PostMapping("/put")
    public Object put(@RequestBody ProductInfo productInfo) {
        cacheMonitor.putProductCache(productInfo.getSkuSn(),productInfo.getProductName(), productInfo.getProductDesc());
        return "success";
    }

    @PostMapping("/getCacheByCacheName")
    public Object getCacheByCacheName(@RequestParam String productCache,@RequestParam String key) {
        return cacheMonitor.getCache(productCache, key);
    }

    @PostMapping("/getAllCache")
    public Object getCacheNames() {
        return cacheMonitor.getCacheNames();
    }


}
