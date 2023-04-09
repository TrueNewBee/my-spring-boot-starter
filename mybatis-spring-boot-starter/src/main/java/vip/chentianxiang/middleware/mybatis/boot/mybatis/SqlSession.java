package vip.chentianxiang.middleware.mybatis.boot.mybatis;

import java.util.List;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/9 15:21
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public interface SqlSession {
    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <T> List<T> selectList(String statement);

    <T> List<T> selectList(String statement, Object parameter);

    void close();
}
