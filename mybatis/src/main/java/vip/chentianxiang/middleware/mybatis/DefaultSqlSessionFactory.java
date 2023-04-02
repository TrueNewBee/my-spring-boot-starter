package vip.chentianxiang.middleware.mybatis;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/1 23:34
 * @Github: https://github.com/TrueNewBee
 * @Description: 实现SessionFactory 接口
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration.connection,configuration.mapperElement);
    }
}
