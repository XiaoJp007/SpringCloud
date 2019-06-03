package com.imooc.order.dto;

import lombok.Data;

/**
 * 商品库存
 */

@Data
public class CartDto {

    //商品id
    private String productId;

    //商品数量
    private Integer productQuantity;
}
