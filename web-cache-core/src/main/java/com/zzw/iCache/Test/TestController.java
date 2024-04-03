//package com.zzw.iCache.Test;
//
//import com.zzw.iCache.demo.Api.Entity.ProductInfo;
//import com.zzw.iCache.demo.Api.TestFacade;
//import jdk.nashorn.internal.ir.annotations.Reference;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//
//
//@RestController
//@Slf4j
//public class TestController {
//
//    @DubboReference(version = "1.0.0", timeout = 5000)
//    private TestFacade testFacade;
//
//    @PostMapping("/put")
//    public Object put(@RequestBody ProductInfo productInfo) {
//        testFacade.put(productInfo);
//        return "success";
//    }
//
//    @PostMapping("/get")
//    public Object put(@RequestParam String key) {
//        return testFacade.get(key);
//    }
//
//    @PostMapping("/test")
//    public Object put() {
//        return "6";
//    }
//
//
//}
