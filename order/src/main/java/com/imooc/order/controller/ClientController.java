package com.imooc.order.controller;

import com.imooc.order.client.ProductClient;
import com.imooc.order.dto.CartDto;
import com.imooc.order.dto.ProductInfoRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/getProductMsg")
    public String getProductMsg() {

//        RestTemplate resultTemplate = new RestTemplate();
//        String result = resultTemplate.getForObject("http://localhost:8090/server/serverMsg", String.class);

//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");

//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/server/serverMsg");
//        String result = restTemplate.getForObject(url,String.class);

//        String result = restTemplate.getForObject("http://PRODUCT/server/serverMsg",String.class);
        String result = productClient.productMsg();
        return result;

    }

    /**
     * 通过商品id查询商品列表
     *
     * @return
     */
    @RequestMapping("/queryProductListByIds")
    public String queryProductListByIds() {

        List<ProductInfoRequestDto> list = productClient.queryProductListByIds(Arrays.asList("157875196366160022"));
        log.info("查询出的商品列表是list{}", list);
        return "success";
    }

    /**
     * 修改商品的存库
     * @return
     */
    @RequestMapping("/updateProductStock")
    public String updateProductStock() {
        List<CartDto> list = new ArrayList<>();
        CartDto productInfo = new CartDto();

        productInfo.setProductId("157875196366160022");
        productInfo.setProductQuantity(1);

        CartDto productInfo1 = new CartDto();

        productInfo1.setProductId("157875227953464068");
        productInfo1.setProductQuantity(2);
        list.add(productInfo);
        list.add(productInfo1);
        productClient.decreaseProductStock(list);

        return "success";
    }

}
