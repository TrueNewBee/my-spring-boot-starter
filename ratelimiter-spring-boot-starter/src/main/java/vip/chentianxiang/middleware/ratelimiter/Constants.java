package vip.chentianxiang.middleware.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/26 18:13
 * @Github: https://github.com/TrueNewBee
 * @Description: 用于存储调用方法名称
 */
public class Constants {

    public static Map<String, RateLimiter> rateLimiterMap = Collections.synchronizedMap(new HashMap<String, RateLimiter>());
    
}
