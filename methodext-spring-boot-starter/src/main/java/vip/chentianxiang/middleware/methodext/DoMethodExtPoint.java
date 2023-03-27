package vip.chentianxiang.middleware.methodext;

import com.alibaba.fastjson.JSON;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import vip.chentianxiang.middleware.methodext.annotation.DoMethodExt;

import java.lang.reflect.Method;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/27 22:43
 * @Github: https://github.com/TrueNewBee
 * @Description: 注解方法
 */
public class DoMethodExtPoint {

    private Logger logger = LoggerFactory.getLogger(DoMethodExtPoint.class);

    @Pointcut("@annotation(vip.chentianxiang.middleware.methodext.annotation.DoMethodExt)")
    public void aopPoint() {

    }

    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取内容
        Method method = getMethod(joinPoint);
        DoMethodExt doMethodExt = method.getAnnotation(DoMethodExt.class);
        // 获取拦截方法
        String methodName = doMethodExt.method();
        // 功能处理
        Method methodEx = getClass(joinPoint).getMethod(methodName, method.getParameterTypes());
        Class<?> returnType = methodEx.getReturnType();

        // 判断方法返回类型
        if (!returnType.getName().equals("boolean")) {
            throw new RuntimeException("annotation @DoMethodExt set method：" + methodName + " returnType is not boolean");
        }
        // 拦截判断正常,继续
        boolean invoke = (boolean) methodEx.invoke(joinPoint.getThis(), joinPoint.getArgs());
        // 返回结果
        return invoke ? joinPoint.proceed() : JSON.parseObject(doMethodExt.returnJson(), method.getReturnType());
    }

    // 获取方法
    private Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // 通过切点注入,获取方法名称, 方法类型
        return joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    private Class<? extends Object> getClass(JoinPoint joinPoint) throws NoSuchMethodException {
        return joinPoint.getTarget().getClass();
    }

}
