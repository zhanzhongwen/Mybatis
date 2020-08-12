package com.yinhai.mybatis.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Setter
@Getter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -3330851033429007657L;
    private String id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private String created;
    private String updated;
}
