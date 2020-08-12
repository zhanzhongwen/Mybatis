package com.yinhai.mybatis.dao.impl;

import com.yinhai.mybatis.dao.UserDao;
import com.yinhai.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDaoImpl implements UserDao {
    public SqlSession sqlSession;
    public UserDaoImpl(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    public User queryUserById(String id) {
        return sqlSession.selectOne("UserDao.queryUserById",id);
    }

    public List<User> queryUserAll() {
        return sqlSession.selectList("UserDao.queryUserAll");
    }

    public void insertUser(User user) {
        sqlSession.insert("UserDao.insertUser",user);

    }

    public void updateUser(User user) {
        sqlSession.update("UserDao.updateUser",user);

    }

    public void deleteUserById(String id) {
        sqlSession.delete("UserDao.deleteUserById",id);
    }
}
