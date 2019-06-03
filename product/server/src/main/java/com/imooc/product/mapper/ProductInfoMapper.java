package com.imooc.product.mapper;

import com.imooc.product.model.ProductInfo;
import com.xiao.product.common.ProductInfoRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoMapper extends JpaRepository<ProductInfo, String> {


    List<ProductInfo> queryProductInfoListByProductStatus(int productStatus);


    /**
     * 通过productIds获取商品列表
     * @param productIds
     * @return
     */
    List<ProductInfo> queryProductListByProductIdIn(List<String> productIds);

}
