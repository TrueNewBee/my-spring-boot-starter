package vip.chentianxiang.middleware.ratelimiter.value.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import vip.chentianxiang.middleware.ratelimiter.Constants;
import vip.chentianxiang.middleware.ratelimiter.annotation.DoRateLimiter;
import vip.chentianxiang.middleware.ratelimiter.value.IValveService;

import java.lang.reflect.Method;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/26 18:14
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public class RateLimiterValue implements IValveService {
    @Override
    public Object access(ProceedingJoinPoint jp, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable {
        // 判断是否开启
        if (0 == doRateLimiter.permitsPerSecond()) return jp.proceed();

        String clazzName = jp.getTarget().getClass().getName();
        String methodName = method.getName();

        String key = clazzName + "." + methodName;

        if (null == Constants.rateLimiterMap.get(key)) {
            Constants.rateLimiterMap.put(key, RateLimiter.create(doRateLimiter.permitsPerSecond()));
        }

        RateLimiter rateLimiter = Constants.rateLimiterMap.get(key);
        if (rateLimiter.tryAcquire()) {
            return jp.proceed();
        }

        return JSON.parseObject(doRateLimiter.returnJson(),method.getReturnType());
    }
}
