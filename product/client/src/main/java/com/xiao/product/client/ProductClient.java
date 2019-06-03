package com.xiao.product.client;


import com.xiao.product.common.DecrProductStockRequestDto;
import com.xiao.product.common.ProductInfoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 作为对外提供的接口
 */

//name的属性值表示需要访问的应用
@FeignClient(name = "product")
public interface ProductClient {

    /**
     * 通过商品id(是一个list)查询商品列表
     *
     * @return
     */
    @PostMapping("/queryProductListByIds")
    List<ProductInfoRequestDto> queryProductListByIds(@RequestBody List<String> productIds);

    /**
     * 减去订单库存
     *
     * @param cartDtoList
     */
    @PostMapping("/decrProductStock")
    void decrProductStock(@RequestBody List<DecrProductStockRequestDto> cartDtoList);
}
