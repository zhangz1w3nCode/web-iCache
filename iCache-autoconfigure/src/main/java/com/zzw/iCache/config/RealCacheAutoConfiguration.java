//package com.zzw.iCache.config;
//
//import com.zzw.iCache.processor.RealCacheBeanPostProcessor;
//import com.zzw.iCache.properties.RealProperties;
//import com.zzw.iCache.CacheManager.CacheManager;
//import com.zzw.iCache.CacheManager.implement.CacheManagerImp;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.core.annotation.Order;
//
///**
// * SeaDog 缓存自动配置类
// */
//
///** 保证优先加载，防止出现找不到缓存对象的问题 */
//@Order(-999999)
//@Configuration
////@AutoConfigureAfter(RealCacheBeanConfig.class)
//@Import(RealCacheBeanPostProcessor.class)
//@EnableConfigurationProperties({RealProperties.class})
//public class RealCacheAutoConfiguration {
//
//    @Bean
//    @ConditionalOnMissingBean(CacheManager.class)
//    public CacheManager cacheManager(){
//        return new CacheManagerImp();
//    }
//
//}
