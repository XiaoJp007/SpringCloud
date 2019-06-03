package com.imooc.product.service;

import com.imooc.product.ProductApplicationTests;
import com.imooc.product.model.ProductInfo;
import com.xiao.product.common.DecrProductStockRequestDto;
import com.xiao.product.common.ProductInfoRequestDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductInfoServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void queryProductInfoListByStatus() throws Exception {
        List<ProductInfo> list = productInfoService.queryProductInfoListByStatus();
        System.out.println(list);

        List<String> productIds = new ArrayList<>();
        productIds.add("157875196366160022");
        productIds.add("164103465734242707");
        List<ProductInfoRequestDto> list1 = productInfoService.queryProductListByIds(Arrays.asList("157875196366160022", "164103465734242707"));
        System.out.println("===============>" + list1);

    }

    @Test
    public void queryProductListByProductIdIn() throws Exception {
        List<String> productIds = new ArrayList<>();
        productIds.add("157875196366160022");
        productIds.add("164103465734242707");
        List<ProductInfoRequestDto> list = productInfoService.queryProductListByIds(Arrays.asList("157875196366160022", "164103465734242707"));
        System.out.println("===============>" + list);

    }

    @Test
    public void decrProductStock() throws Exception {
        List<DecrProductStockRequestDto> cartDtoList = new ArrayList<>();
        DecrProductStockRequestDto requestDto1 = new DecrProductStockRequestDto();
        requestDto1.setProductId("157875196366160022");
        requestDto1.setProductQuantity(2);

        DecrProductStockRequestDto requestDto2 = new DecrProductStockRequestDto();
        requestDto2.setProductId("157875227953464068");
        requestDto2.setProductQuantity(-2);
        cartDtoList.add(requestDto1);
        cartDtoList.add(requestDto2);
        productInfoService.decrProductStock(cartDtoList);
    }

}