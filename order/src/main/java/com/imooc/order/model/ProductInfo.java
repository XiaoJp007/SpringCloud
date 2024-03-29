package com.imooc.order.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    //商品单价
    private BigDecimal productPrice;

    //库存
    private Integer productStock;

    //商品描述
    private String productDescription;

    //小图
    private String productIcon;

    //商品状态,0正常1下架
    private Integer productStatus;

    //类目编号
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;



}
