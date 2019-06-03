package com.xiao.product.common;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoRequestDto {


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
}
