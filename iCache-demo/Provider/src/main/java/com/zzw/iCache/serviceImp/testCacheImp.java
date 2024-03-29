package com.zzw.iCache.serviceImp;


import com.zzw.iCache.Cache.Cache;
import com.zzw.iCache.Entity.ProductInfo;
import com.zzw.iCache.TestFacade;
import com.zzw.iCache.annocation.iCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@Service(version = "1.0.0")
@Slf4j
public class testCacheImp implements TestFacade {

    @iCache(name = "productInfoCache")
    Cache<ProductInfo> productInfoCache;

    @Override
    public Object put(ProductInfo productInfo) {
        //使用skuSn加今天的时间的年月日作为key
        //获取当前年月日作为字符串
        String currenDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = productInfo.getSkuSn() + "_" + currenDate;;
        productInfoCache.put(key, productInfo);
        //把key和value打印出来，方便查看
        log.info("key:{},value:{}", key, productInfo);
        return key;
    }

    @Override
    public Object get(String key) {
        ProductInfo productInfo = productInfoCache.get(key);
        log.info("key:{},value:{}", key, productInfo);
        return productInfo;
    }
}
