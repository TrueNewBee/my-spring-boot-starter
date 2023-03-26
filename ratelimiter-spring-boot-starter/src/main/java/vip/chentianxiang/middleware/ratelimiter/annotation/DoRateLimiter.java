package vip.chentianxiang.middleware.ratelimiter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/26 18:14
 * @Github: https://github.com/TrueNewBee
 * @Description: 注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoRateLimiter {

    double permitsPerSecond() default 0D;   // 限流许可量

    String returnJson() default "";         // 失败结果 JSON

}
