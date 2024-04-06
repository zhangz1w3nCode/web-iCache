package com.zzw.iCache.Controller;
import com.zzw.iCache.monitor.CacheMonitor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/web")
public class MonitorController {

    @DubboReference(version = "1.0.0", timeout = 5000)
    private CacheMonitor cacheMonitor;


    @PostMapping("/getCacheByCacheName")
    public Object getCacheByCacheName(@RequestParam String productCache,@RequestParam String key) {
        return cacheMonitor.getCache(productCache, key);
    }

    @PostMapping("/getAllCache")
    public Object getCacheNames() {
        return cacheMonitor.getCacheNames();
    }

}
