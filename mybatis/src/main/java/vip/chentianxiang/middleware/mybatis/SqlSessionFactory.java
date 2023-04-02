package vip.chentianxiang.middleware.mybatis;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/1 23:34
 * @Github: https://github.com/TrueNewBee
 * @Description: session 工厂接口
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
