package com.zzw.iCache.controller;
import com.zzw.iCache.Utils.StreamUtils;
import com.zzw.iCache.Utils.WebResult;

import com.zzw.iCache.monitor.dubbo.domain.Provider;
import com.zzw.iCache.monitor.dubbo.dubboApi.CacheMonitor;
import com.zzw.iCache.monitor.dubbo.dubboApi.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {


    @Autowired
    private ProviderService providerService;


    /**
     * 参数说明：
     * 业务方应用名称
     * 使用的缓存名称
     * 业务方服务的ip地址
     * 要查询的缓存key
     */

    /**
     * 获取应用和提供者列表
     * @return
     */
    @GetMapping("/applications")
    public WebResult<List<String>> getCacheByCacheName() {
        List<Provider> providerList = providerService.findByService(CacheMonitor.class.getName());
        List<String> ApplicationSet = StreamUtils.mapAndToList(providerList, Provider::getApplication);
        return WebResult.success(new ArrayList<>(ApplicationSet));
    }

}
