package com.yinhai.mybatis.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetail {
    private Integer id;
    private Integer order_id;
    private Double totalPrice;
    private Integer status;
    private Item item;
}
