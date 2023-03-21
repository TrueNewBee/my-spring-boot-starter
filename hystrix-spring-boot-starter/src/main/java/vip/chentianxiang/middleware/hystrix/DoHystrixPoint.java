package vip.chentianxiang.middleware.hystrix;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import vip.chentianxiang.middleware.hystrix.annotation.DoHystrix;
import vip.chentianxiang.middleware.hystrix.value.IValveService;
import vip.chentianxiang.middleware.hystrix.value.impl.HystrixValveImpl;

import java.lang.reflect.Method;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/21 20:28
 * @Github: https://github.com/TrueNewBee
 * @Description: 熔断操作具体切面逻辑
 */
@Aspect
@Component
public class DoHystrixPoint {

    @Pointcut("@annotation(vip.chentianxiang.middleware.hystrix.annotation.DoHystrix)")
    public void aopPoint(){
    }

    @Around("aopPoint() && @annotation(doGovern)")
    public Object doRouter(ProceedingJoinPoint jp, DoHystrix doGovern) throws Throwable{
        IValveService valveService = new HystrixValveImpl();
        return valveService.access(jp, getMethod(jp), doGovern, jp.getArgs());
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException{
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(),methodSignature.getParameterTypes());
    }

}
