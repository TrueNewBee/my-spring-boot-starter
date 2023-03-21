package vip.chentianxiang.middleware.hystrix.value;

import org.aspectj.lang.ProceedingJoinPoint;
import vip.chentianxiang.middleware.hystrix.annotation.DoHystrix;

import java.lang.reflect.Method;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/21 20:31
 * @Github: https://github.com/TrueNewBee
 * @Description: 熔断服务接口
 */
public interface IValveService {

    Object access(ProceedingJoinPoint jp, Method method, DoHystrix doHystrix, Object[] args) throws Throwable;

}
