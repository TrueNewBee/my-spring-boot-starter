package vip.chentianxiang.middleware.mybatis.boot.mybatis;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/9 15:21
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration.connection, configuration.mapperElement);
    }
}
