package vip.chentianxiang.middleware.ratelimiter.value;

import org.aspectj.lang.ProceedingJoinPoint;
import vip.chentianxiang.middleware.ratelimiter.annotation.DoRateLimiter;
import java.lang.reflect.Method;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/26 18:14
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public interface IValveService {

    Object access(ProceedingJoinPoint jp, Method method, DoRateLimiter doRateLimiter, Object[] args) throws Throwable;

}
