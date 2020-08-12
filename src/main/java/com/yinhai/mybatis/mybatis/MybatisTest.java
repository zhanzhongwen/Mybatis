package com.yinhai.mybatis.mybatis;

import com.yinhai.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //指定全局配置文件
        String resource = "mybatis-config.xml";
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //操作CRUD，第一个参数，指定statement,第二个参数：指定传入sql的参数
        try{
            User user = sqlSession.selectOne("MyMapper.selectUser",1);
            System.out.println(user);
        }finally {
            sqlSession.close();
        }

    }

}
