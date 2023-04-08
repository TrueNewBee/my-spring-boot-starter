package test.dao;


import test.po.User;

public interface IUserDao {

     User queryUserInfoById(Long id);

}
