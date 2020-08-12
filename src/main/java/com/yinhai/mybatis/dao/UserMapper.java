package com.yinhai.mybatis.dao;

import com.yinhai.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserMapper {
    /**
     * 登录 直接使用注解指定传入参数的名称
     * @param user_name
     * @param password
     * @return
     */
    public User login(@Param("user_name") String user_name,@Param("password") String password);
    /*public User login(String user_name,String password);*/
    /**
     * 根据表名查询用户信息
     * @param tableName
     * @return
     */
    /*通常在方法的参数列表上加上一个注释@Param(“xxxx”) 显式指定参数的名字，然后通过${“xxxx”}或#{“xxxx”}
    sql语句动态生成的时候，使用${};
    sql语句中某个参数进行占位的时候#{}*/
    public List<User> queryUserByTableName(@Param("tableName") String tableName);

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
     * 新增用户信息
     * @param user
     */
    public void insertUser(User user);

    /**
     * 根据id更新用户信息
     * @param user
     */

    public void updateUser(User user);

     /**
     * 根据id删除用户信息
     * @param id
     */
    public void deleteUserById(Long id);
     //#和 $符号的差别  sql语句动态生成的时候，使用${};
    //sql语句中某个参数进行占位的时候#{}
    /**
     * #号
     * @param username1
     * @return
     */
    public User queryUserListByName1(@Param("username1") String username1);

    /**
     * $号
     * @param username2
     * @return
     */
    public User queryUserListByName2(@Param("username2") String username2);

    //以下为动态SQL实践
    //场景一：查询男性用户，如果输入了姓名，则按姓名查询  if
    List<User> queryUserList(@Param("name") String name);

    /**
     * 查询男性用户，如果输入了姓名则按照姓名模糊查找，否则如果输入了年龄则按照年龄查找，
     * 否则查找姓名为“鹏程”的用户。
     * choose when otherwise
     * @param name
     * @param age
     * @return
     */
    List<User> queryUserListByNameOrAge(@Param("name") String name,@Param("age") Integer age);
    /**
     * 查询所有用户，如果输入了姓名按照姓名进行模糊查询，如果输入年龄
     * ，按照年龄进行查询，如果两者都输入，两个条件都要成立
     * where set
     * @param name
     * @param age
     * @return
     */
    List<User> queryUserListByNameAndAge(@Param("name") String name,@Param("age") Integer age);
    /**
     * 根据id更新用户信息,如果参数user中某个属性为null，则不修改
     *
     * @param user
     */
    public void updateUserById(User user);
    /**
     * 按多个Id查询 foreach
     * @param ids
     * @return
     */
    List<User> queryUserListByIds(@Param("ids") String[] ids);
    /**
     * 逻辑分页
     */
    List<User> selectUserList(RowBounds rowBounds);
}
