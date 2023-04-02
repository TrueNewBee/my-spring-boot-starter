package cn.bugstack.middleware.mybatis.test;


import cn.bugstack.middleware.mybatis.test.po.User;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import vip.chentianxiang.middleware.mybatis.*;

import java.io.Reader;
import java.util.List;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public class ApiTest {

    @Test
    public void test_queryUserInfoById() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
             reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = sqlMapper.openSession();

            try {
                User user = sqlSession.selectOne("cn.bugstack.middleware.mybatis.test.dao.IUserDao.queryUserInfoById", 3L);
                System.out.println(JSON.toJSONString(user));
            }finally {
                sqlSession.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_queryUserList() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSession();
            try {
                User req = new User();
                req.setUserNickName("团团");
                List<User> userList = session.selectList("cn.bugstack.middleware.mybatis.test.dao.IUserDao.queryUserList", req);
                System.out.println(JSON.toJSONString(userList));
            } finally {
                session.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
