package com.zzw.iCache.controller;

import com.alibaba.fastjson.JSON;
import com.zzw.iCache.Utils.StreamUtils;
import com.zzw.iCache.Utils.WebResult;

import com.zzw.iCache.entity.vo.CacheDataVo;
import com.zzw.iCache.entity.vo.CacheInfoVo;
import com.zzw.iCache.entity.vo.CacheSnapshotVo;
import com.zzw.iCache.monitor.dubbo.domain.Provider;
import com.zzw.iCache.monitor.CacheMonitor;
import com.zzw.iCache.monitor.dubbo.dubboApi.ProviderService;
import com.zzw.iCache.monitor.dubbo.dubboUtils.DubboGeneralizeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.registry.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {


    @Autowired
    private ProviderService providerService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    /**
     * 1.业务方应用名称 列表
     *
     * 2.获取某个业务方应用名称下的 提供者信息列表
     *
     * 3.获取某个业务方应用名称下的ip地址 列表
     *
     * 4.获取某个业务方应用名称下 某个ip地址的 缓存名称列表
     *
     * 5.获取某个业务方应用名称下 某个ip地址的 某个缓存名 对应的key列表
     *
     * 6.获取某个业务方应用名称下 某个ip地址的 某个缓存名 某个key的值
     */


    /**
     * 获取 服务提供者的 列表 也就是 业务方应用名称
     *
     * @return
     */
    @GetMapping("/applications")
    public WebResult<Set<String>> getCacheByCacheName() {
        List<Provider> providerList = providerService.findByService(CacheMonitor.class.getName());
        Set<String> ApplicationSet = StreamUtils.mapAndToSet(providerList, Provider::getApplication);
        return WebResult.success(ApplicationSet);
    }


    /**
     * 根据业务方应用名称 查询 服务提供者的具体信息
     *
     * @return
     */
    @GetMapping("servers")
    public WebResult<List<Provider>> servers(String application) {
        List<Provider> providers = providerService.findByService(CacheMonitor.class.getName());
        List<Provider> providerList = StreamUtils.filterAndToList(providers, p -> StringUtils.equals(application, p.getApplication()));
        return WebResult.success(providerList);
    }

    /**
     * 根据业务方应用名称 查询 服务提供者的ip地址分布情况
     *
     * @return
     */
    @GetMapping("serversIps")
    public WebResult<List<String>> serversIps(String applicationName) {
        List<Provider> providers = providerService.findByService(CacheMonitor.class.getName());
        List<Provider> providerList = StreamUtils.filterAndToList(providers, p -> StringUtils.equals(applicationName, p.getApplication()));
        List<String> ipList = new ArrayList<>();
        //获取对象集合的url信息 并且将其信息截取 获取ip地址
        if(CollectionUtils.isNotEmpty(providerList)){
            ipList = providerList.stream().map(Provider -> Provider.getAddress()).collect(Collectors.toList());
        }
        return WebResult.success(ipList);
    }


    /**
     * 查询 某个应用 在某个ip 的dubbo接口(获取这个业务方应用的使用的所有缓存名称)
     *
     * @return
     */
    @GetMapping("names")
    public WebResult<Set<String>> getCacheNames(String application, String address) {
        if (StringUtils.isEmpty(address)) {
            List<Provider> providers = providerService.findByService(CacheMonitor.class.getName());
            Optional<Provider> any = providers.stream().filter(p -> p.getApplication().equalsIgnoreCase(application)).findAny();
            address = any.get().getAddress();
        }
        CacheMonitor facadeImpl = DubboGeneralizeUtils.getFacadeImpl(CacheMonitor.class, address, "");
        return WebResult.success(facadeImpl.getCacheNames());
    }

    /**
     * @param addressList 业务方应用名称：172.20.10.13:20880
     * @param cacheName   缓存名称：productCache
     *                    &#064;Description：传入服务提供者ip地址和缓存名称去查询该缓存名称下所有的key
     * @return 返回所有key
     */
    @GetMapping("keys")
    public WebResult<Set<String>> getCacheKeys(String addressList, String cacheName) {
        Set<String> result = new HashSet<>();
        for (String address : addressList.split(",")) {

            CacheMonitor facadeImpl = DubboGeneralizeUtils.getFacadeImpl(CacheMonitor.class, address, "");
            result.addAll(facadeImpl.getCacheKeys(cacheName));
        }
        return WebResult.success(result);
    }

    /**
     * @param address 业务方应用ip：172.20.10.13:20880
     * @param cacheName   缓存名称：productCache
     *&#064;Description：查询具体业务方的 具体ip 下 某个缓存的key的数量
     * @return 返回所有key
     */
    @GetMapping("cacheSize")
    public WebResult<Long> cacheSize(String address, String cacheName) {
        CacheMonitor facadeImpl = DubboGeneralizeUtils.getFacadeImpl(CacheMonitor.class, address, "");
        return WebResult.success(facadeImpl.cacheSize(cacheName));
    }

    /**
     * @param addressList 业务方应用名称：172.20.10.13:20880
     * @param cacheName   缓存名称：productCache
     * @param key         缓存的key：    20240409_101_237667512
     *                    &#064;Description：传入服务提供者ip地址和缓存名称以及缓存的key去查询该缓存的值
     * @return 返回所有key
     */
    @GetMapping("queryCacheValue")
    public WebResult<List<CacheDataVo>> queryCacheValue(String addressList, String cacheName, String key) {
        List<CacheDataVo> result = new ArrayList<>();
        for (String address : addressList.split(",")) {
            CacheMonitor facadeImpl = DubboGeneralizeUtils.getFacadeImpl(CacheMonitor.class, address, "");
            Object cache = facadeImpl.getCache(cacheName, key);
            CacheDataVo vo = new CacheDataVo(address, JSON.toJSONString(cache));
            result.add(vo);
        }
        return WebResult.success(result);
    }

    /**
     * @param application 业务方应用名称：biz-product
     * @param cacheName   缓存名称：productCache
     * @return 返回根据业务名称和缓存名称 查询这个服务所有机器的缓存数量
     */
    @GetMapping("queryCacheInfo")
    public WebResult<List<CacheInfoVo>> queryCacheInfo(String application, String cacheName) {
        List<CacheInfoVo> result = new ArrayList<>();

        for (Provider provider : providerService.findByService(CacheMonitor.class.getName())) {

            if (provider.getApplication().equalsIgnoreCase(application)) {

                CacheMonitor facadeImpl = DubboGeneralizeUtils.getFacadeImpl(CacheMonitor.class, provider.getAddress(), "");
                
                long count = facadeImpl.cacheSize(cacheName);
                //Set<String> refreshNames = facadeImpl.getRefreshName(cacheName);
                Set<String> refreshNames = new HashSet<>();
                CacheInfoVo vo = new CacheInfoVo(provider.getAddress(), count, refreshNames);
                result.add(vo);
            }

        }

        return WebResult.success(result);
    }

//    @RequestMapping("queryCacheSnapshot")
//    public WebResult<List<CacheSnapshotVo>> queryCacheSnapshot(String addressList, String cacheName, String key){
//        List<CacheSnapshotVo> result = new ArrayList<>();
//        for (String address : addressList.split(",")) {
//
//            CacheMonitor facadeImpl = DubboGeneralizeUtils.getFacadeImpl(CacheMonitor.class, address, "");
//            List<SnapshotEntity> cache = facadeImpl.getCacheSnapshot(cacheName, key);
//            for (SnapshotEntity snapshotEntity : cache) {
//                CacheSnapshotVo vo = new CacheSnapshotVo(address, snapshotEntity);
//                result.add(vo);
//            }
//        }
//
//        result.sort((o1, o2) ->  o2.getTmCreate().compareTo(o1.getTmCreate()));
//        return WebResult.success(result);
//    }
//

    //主动刷新缓存方法
    @RequestMapping("refreshCache")
    public WebResult refreshCache(String application, String address, String cacheName, String refreshName){
        // 调用某一台机器的刷新器
        if(StringUtils.isNotBlank(address)) {
            CacheMonitor facadeImpl = DubboGeneralizeUtils.getFacadeImpl(CacheMonitor.class, address, "");
            facadeImpl.refreshCache(cacheName, refreshName);
            return WebResult.success();
        }


        List<CompletableFuture> futureList = new ArrayList<>();

        List<Provider> providers = providerService.findByService(CacheMonitor.class.getName());

        providers.stream().filter(p -> application.equalsIgnoreCase(p.getApplication())).forEach(p ->{
            futureList.add(CompletableFuture.runAsync(() -> {
                CacheMonitor facadeImpl = DubboGeneralizeUtils.getFacadeImpl(CacheMonitor.class, p.getAddress(), "");
                facadeImpl.refreshCache(cacheName, refreshName);
            }, threadPoolTaskExecutor));
        });

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));

        return WebResult.success();
    }

}
