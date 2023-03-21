package vip.chentianxiang.middleware.whitelist.annotation;

import java.lang.annotation.*;

/**
 * @Author: TrueNewBee
 * @Date: 2023-3-21 11:13:47
 * @Github: https://github.com/TrueNewBee
 * @Description: 元注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoWhiteList {

    String key() default "";

    String returnJson() default "";

}
