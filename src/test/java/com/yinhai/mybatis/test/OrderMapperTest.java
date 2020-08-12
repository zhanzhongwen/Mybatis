package com.yinhai.mybatis.test;

import com.yinhai.mybatis.dao.OrderMapper;
import com.yinhai.mybatis.dao.UserDao;
import com.yinhai.mybatis.pojo.Order;
import com.yinhai.mybatis.pojo.OrderUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * 测试mybatis高级查询
 */
public class OrderMapperTest {
    private OrderMapper orderMapper;
    @Before
    public void setUp() throws  Exception{
        //mybatis-config.xml
        String resource = "mybatis-config.xml";
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建sqlsessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //活得sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //this.userDao = new UserDaoImpl(sqlSession);
        //动态代理
        //如果这里改为动态代理，要在UserMapper.xml中配置接口的全路径
        //如果使用Mybatis的DAO接口动态代理，则namespace必须为DAO接口的全路径，例如：com.yinhai.mybatis.dao.UserDao
        this.orderMapper = sqlSession.getMapper(OrderMapper.class);
    }
    //一个订单对应一个订单人 一对一
    @Test
    public void queryOrderUserByOrderNumber(){
        OrderUser orderUser = orderMapper.queryOrderUserByOrderNumber("201807010001");
        System.out.println(orderUser);

    }
    //一个订单对应一个订单人 方法二，面向对象，Order类中添加User类
    @Test
    public void queryOrderWithUserByOrderNumber() throws Exception {
        Order order = orderMapper.queryOrderWithUserByOrderNumber("201807010001");
        System.out.println(order.getUser());
    }
    //一对多
    @Test
    public void queryOrderWithUserAndDetailByOrderNumber() throws Exception {
        Order order = orderMapper.queryOrderWithUserAndDetailByOrderNumber("201807010003");
        System.out.println(order.getUser());
        System.out.println(order.getDetailList());
    }
    //多对多
    @Test
    public void queryOrderWithUserAndDetailItemByOrderNumber() throws Exception {
        Order order = orderMapper.queryOrderWithUserAndDetailItemByOrderNumber("201807010001");
        System.out.println(order);
        System.out.println(order.getUser());
        System.out.println(order.getDetailList());
    }

   @Test
   public void testQueryOrderAndUserByOrderNumberLazy(){
        Order order = orderMapper.queryOrderAndUserByOrderNumberLazy("201807010004");
        System.out.println(order.getOrderNumber());
        System.out.println("====================================");
        System.out.println(order.getUser());
   }


}
