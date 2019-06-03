package com.imooc.product.service;

import com.imooc.product.model.ProductInfo;
import com.xiao.product.common.DecrProductStockRequestDto;
import com.xiao.product.common.ProductInfoRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductInfoService {

    List<ProductInfo> queryProductInfoListByStatus();

    /**
     * 通过productIds查询商品列表
     *
     * @param productIds
     * @return
     */
    List<ProductInfoRequestDto> queryProductListByIds(List<String> productIds);

    /**
     * 减去商品库存 因为用户可能会一次购买多个商品，所以这里是一个结合
     *
     * @param cartDtoList
     */
    void decrProductStock(List<DecrProductStockRequestDto> cartDtoList);


}
