package com.zzw.iCache.annocation;

import com.zzw.iCache.CacheConstant.CacheConstant;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 缓存注解，可以在使用注解开启一个缓存
 * @author qiaolin
 * @version $Id:  SeaDogCache.java,v 0.1 2020年06月20日 16:09 $Exp
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface iCache {

    @AliasFor(annotation = iCache.class, value = "name")
    String value() default "";

    @AliasFor(annotation = iCache.class, value = "value")
    String name() default "";

    String type() default CacheConstant.CACHE_TYPE_CAFFEINE;

    String[] filter() default {};

    String[] listener() default {};
}
