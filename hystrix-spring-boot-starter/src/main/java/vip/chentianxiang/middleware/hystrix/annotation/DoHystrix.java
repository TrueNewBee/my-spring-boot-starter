package vip.chentianxiang.middleware.hystrix.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/21 20:31
 * @Github: https://github.com/TrueNewBee
 * @Description: 元注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoHystrix {

    String returnJson() default "";         // 失败结果 JSON

    int timeoutValue() default 0;           // 超时熔断

}
