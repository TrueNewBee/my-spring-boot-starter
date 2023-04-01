package vip.chentianxiang.middleware.mybatis;

import java.util.List;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/1 23:34
 * @Github: https://github.com/TrueNewBee
 * @Description: 定义Session接口
 */
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <T> List<T> selectList(String statement);

    <T> List<T> selectList(String statement, Object parameter);

    void close();


}
