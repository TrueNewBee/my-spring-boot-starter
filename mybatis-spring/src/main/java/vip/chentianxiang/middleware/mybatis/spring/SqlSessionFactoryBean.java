package vip.chentianxiang.middleware.mybatis.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import vip.chentianxiang.middleware.mybatis.Resources;
import vip.chentianxiang.middleware.mybatis.SqlSessionFactory;
import vip.chentianxiang.middleware.mybatis.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/8 21:50
 * @Github: https://github.com/TrueNewBee
 * @Description: 实现Session工厂
 */
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean {

    private String resource;
    private SqlSessionFactory sqlSessionFactory;

    // 初始化sqlSessionFactory
    @Override
    public void afterPropertiesSet() throws Exception {
        try(Reader reader = Resources.getResourceAsReader(resource)){
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public SqlSessionFactory getObject() throws Exception {
        return sqlSessionFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return SqlSessionFactory.class;
    }

    @Override
    public boolean isSingleton(){
        return true;
    }

    public  void setResource(String resource){
        this.resource = resource;
    }


}
