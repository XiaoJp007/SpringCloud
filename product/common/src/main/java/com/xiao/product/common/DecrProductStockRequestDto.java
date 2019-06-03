package com.xiao.product.common;

import lombok.Data;

/**
 * 商品库存
 */

@Data
public class DecrProductStockRequestDto {

    //商品id
    private String productId;

    //商品数量
    private Integer productQuantity;
}
