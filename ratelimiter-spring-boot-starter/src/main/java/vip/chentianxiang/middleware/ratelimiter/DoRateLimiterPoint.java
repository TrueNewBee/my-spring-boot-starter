package vip.chentianxiang.middleware.ratelimiter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import vip.chentianxiang.middleware.ratelimiter.annotation.DoRateLimiter;
import vip.chentianxiang.middleware.ratelimiter.value.impl.RateLimiterValue;

import java.lang.reflect.Method;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/26 18:13
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
@Aspect
@Component
public class DoRateLimiterPoint {

    @Pointcut("@annotation(vip.chentianxiang.middleware.ratelimiter.annotation.DoRateLimiter)")
    public void aopPoint() {
    }

    @Around("aopPoint() && @annotation(doRateLimiter)")
    public Object doRouter(ProceedingJoinPoint jp, DoRateLimiter doRateLimiter) throws Throwable {
        RateLimiterValue rateLimiterValue = new RateLimiterValue();
        return rateLimiterValue.access(jp, getMethod(jp), doRateLimiter, jp.getArgs());
    }

    private Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

}
