package com.yinhai.mybatis.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Item {
    private Integer id;
    private String  itemName;
    private Float itemPrice;
    private String itemDetail;
}
