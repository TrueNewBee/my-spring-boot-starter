package vip.chentianxiang.middleware.mybatis.boot.mybatis;

/**
 * @Author: TrueNewBee
 * @Date: 2023/4/9 15:21
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
