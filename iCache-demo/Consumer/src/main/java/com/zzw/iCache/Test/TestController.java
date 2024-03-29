package com.zzw.iCache.Test;

import com.zzw.iCache.Entity.ProductInfo;
import com.zzw.iCache.TestFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class TestController {

   @DubboReference
    TestFacade testFacade;

    @PostMapping("/put")
    public Object put(@RequestParam String key,@RequestBody ProductInfo productInfo) {
        testFacade.put(key, productInfo);
        return "success";
    }

    @PostMapping("/get")
    public Object put(@RequestParam String key) {
        return testFacade.get();
    }

}
