package com.yinhai.mybatis.dao;

import com.yinhai.mybatis.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public User queryUserById(String id);

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> queryUserAll();

    /**
     * 新增
     * @param user
     */
    public void insertUser(User user);

    /**
     * 新增
     * @param user
     */
    public void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    public void deleteUserById(String id);
}
