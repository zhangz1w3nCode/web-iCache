package com.zzw.iCache.annocation;

import com.zzw.iCache.config.RealCacheAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开始iCache缓存
 * @author qiaolin、clark
 * @version $Id:  EnableSeaDog.java,v 0.1 2020年06月15日 15:52 $Exp
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(RealCacheAutoConfiguration.class)
public @interface EnableICache {
}
