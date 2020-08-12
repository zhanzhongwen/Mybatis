package com.yinhai.mybatis.test;

import com.yinhai.mybatis.dao.UserDao;
import com.yinhai.mybatis.dao.impl.UserDaoImpl;
import com.yinhai.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDaoTest {
    public UserDao userDao;
    public SqlSession sqlSession;

    @Before
    public void setUp() throws  Exception{
        //mybatis-config.xml
        String resource = "mybatis-config.xml";
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建sqlsessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //活得sqlsession
        sqlSession = sqlSessionFactory.openSession();
        //this.userDao = new UserDaoImpl(sqlSession);
        //动态代理
        //如果这里改为动态代理，要在UserMapper.xml中配置接口的全路径
        //如果使用Mybatis的DAO接口动态代理，则namespace必须为DAO接口的全路径，例如：com.yinhai.mybatis.dao.UserDao
        this.userDao = sqlSession.getMapper(UserDao.class);
    }
   /* @Test
    public void queryUserById() {
        System.out.println(this.userDao.queryUserById("2"));
    }*/
    @Test
    public void queryUserAll() {
        List<User> list = this.userDao.queryUserAll();
        for(User u : list){
            System.out.println(u);
        }
    }
/*    @Test
    public void insertUser() throws Exception {
        User user = new User();
        user.setAge(16);
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd")
                .parse("2020-07-19"));
        user.setName("大鹏展翅");
        user.setPassword("123456");
        user.setSex(1);
        user.setUser_name("伊万若夫");
        user.setId("3");
        this.userDao.insertUser(user);
        this.sqlSession.commit();
    }*/
}
