package com.imooc.order.client;

import com.imooc.order.dto.CartDto;
import com.imooc.order.dto.ProductInfoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//name的属性值是表示需要访问product这个应用
@FeignClient(name = "product")
@Service
public interface ProductClient {

    /**
     * 表示访问product服务/server下面的/serverMsg这个接口
     * 接口名称可以和product中的不一致
     *
     * @return
     */
    @GetMapping("/server/serverMsg")
    String productMsg();

    /**
     * 通过商品id获取商品列表
     *
     * @param productIds
     * @return
     */
    @GetMapping("/product/queryProductListByIds")
    List<ProductInfoRequestDto> queryProductListByIds(@RequestBody List<String> productIds);

    /**
     * 扣除订单库存
     *
     * @param cartDtoList
     */
    @GetMapping("/product/decrProductStock")
    void decreaseProductStock(List<CartDto> cartDtoList);


}
