package com.yinhai.mybatis.dao;


import com.yinhai.mybatis.pojo.Order;
import com.yinhai.mybatis.pojo.OrderUser;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    public OrderUser queryOrderUserByOrderNumber(@Param("number") String number);

    /**
     * 根据订单号查询订单用户的信息，面向对象，Order订单中添加User对象
     * @param number
     * @return
     */
    public Order queryOrderWithUserByOrderNumber(@Param("number") String number);
    /**
     * 根据订单号查询订单用户的信息及订单详情
     * @param number
     * @return
     */
    Order queryOrderWithUserAndDetailByOrderNumber(@Param("number") String number);

    /**
     * 根据订单号查询订单用户的信息及订单详情及订单详情对应的商品信息  多对多查询
     * @param number
     * @return
     */
    Order queryOrderWithUserAndDetailItemByOrderNumber(@Param("number") String number);
     public Order queryOrderAndUserByOrderNumberLazy(@Param("number") String number);
}
