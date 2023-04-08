package vip.chentianxiang.middleware.mybatis.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import vip.chentianxiang.middleware.mybatis.SqlSessionFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


/**
 * @Author: TrueNewBee
 * @Date: 2023/4/8 21:35
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Logger logger = LoggerFactory.getLogger(MapperFactoryBean.class);

    private Class<T> mapperInterface;
    private SqlSessionFactory sqlSessionFactory;

    public MapperFactoryBean(Class<T> mapperInterface, SqlSessionFactory sqlSessionFactory) {
        this.mapperInterface = mapperInterface;
        this.sqlSessionFactory = sqlSessionFactory;
    }


    @Override
    public T getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            logger.info("你被代理了,执行SQL操作! {}",method.getName());
            if ("toString".equals(method.getName())) return null;   // 排除Object方法
            try{
                return sqlSessionFactory.openSession().selectOne(mapperInterface.getName() + "." + method.getName(), args[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
            return method.getReturnType().newInstance();
        };

        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{mapperInterface},handler);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
