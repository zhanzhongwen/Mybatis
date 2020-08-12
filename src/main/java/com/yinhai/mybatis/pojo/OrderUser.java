package com.yinhai.mybatis.pojo;

import java.util.Date;

/**
 * 一对一查询：方法一，扩展Order对象，来完成映射
 */
public class OrderUser extends Order{
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private Date created;
    private Date updated;

}
