package com.imooc.order.controller;


import com.imooc.order.builder.OrderBuilder;
import com.imooc.order.client.ProductClient;
import com.imooc.order.dto.*;
import com.imooc.order.enums.ExceptionCode;
import com.imooc.order.exeception.OrderException;
import com.imooc.order.mapper.OrderDetailMapper;
import com.imooc.order.model.OrderDetail;
import com.imooc.order.service.OrderService;
import com.imooc.order.utils.DecimalUtil;
import com.imooc.order.utils.JsonParser;
import com.imooc.order.utils.SequenceGenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param requestDto
     * @param bindingResult
     * @return
     * @throws OrderException
     */
    @RequestMapping("/createOrder")
    public Map<String, String> createOrder(@Valid OrderFrontRequestDto requestDto, BindingResult bindingResult) throws OrderException {
        log.info("创建订单参数请求requestDto{}", requestDto);
        if (bindingResult.hasErrors()) {
            log.error("创建订单参数不合法requestDto[{}]", requestDto);
            throw new OrderException(ExceptionCode.QUERY_PARAMS_ERROR.getErrorCode(), ExceptionCode.QUERY_PARAMS_ERROR.getErrorMsg());
        }

        OrderRequestDto orderRequestDto = OrderBuilder.builderOrderInfo(requestDto);
        if (CollectionUtils.isEmpty(orderRequestDto.getOrderList())) {
            log.error("创建订单参数不合法requestDto[{}]", requestDto);
            throw new OrderException(ExceptionCode.QUERY_PARAMS_ERROR.getErrorCode(), ExceptionCode.QUERY_PARAMS_ERROR.getErrorMsg());

        }

//        String orderId = SequenceGenUtils.getUniqueKey();
//
//        //查询商品信息
//        List<CartDto> cartDtoList = JsonParser.parserJsonStringToJavaArray(requestDto.getItems(), CartDto.class);
//
//        List<String> productIdList = new ArrayList<>();
//
//        for (CartDto cartDto : cartDtoList) {
//            String productId = cartDto.getProductId();
//            productIdList.add(productId);
//        }
//
//        List<ProductInfoRequestDto> productInfoList = productClient.queryProductListByIds(productIdList);
//
//        //计算商品的金额 数量 * 单价
//        BigDecimal orderTotalAmount = BigDecimal.ZERO;
//        for (ProductInfoRequestDto productInfo : productInfoList) {
//            for (CartDto cartDto : cartDtoList) {
//                if (productInfo.getProductId().equals(cartDto.getProductId())) {
//                    BigDecimal orderAmount = DecimalUtil.multiply(new BigDecimal(cartDto.getProductQuantity()), productInfo.getProductPrice());
//                    orderTotalAmount = DecimalUtil.add(orderTotalAmount, orderAmount);
//                    BeanUtils.copyProperties(orderRequestDto, productInfo);
//
//                    //保存商品详情
//                    OrderDetail orderDetail = new OrderDetail();
//                    BeanUtils.copyProperties(productInfo,orderDetail);
//                    orderDetail.setOrderId(orderId);
//                    orderDetail.setCreateTime(new Date());
//                    orderDetail.setProductPrice(orderAmount);
//                    orderDetailMapper.save(orderDetail);
//                }
//
//            }
//
//        }
//        //扣库存
//        log.info("扣除商品库存开始");
//        productClient.decreaseProductStock(cartDtoList);
//
//        log.info("商品库存扣除成功,开始创建订单", orderRequestDto.getOrderId());
//        BeanUtils.copyProperties(requestDto, orderRequestDto);
//        orderRequestDto.setOrderId(orderId);
//        orderRequestDto.setOrderAmount(orderTotalAmount);
//        orderRequestDto.setCreateTime(new Date());
//        orderRequestDto.setOrderStatus(1);
//        orderRequestDto.setPayStatus(1);

        orderService.createOrderInfo(requestDto, orderRequestDto);

        log.info("orderId{}创建成功", orderRequestDto.getOrderId());
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("orderId", orderRequestDto.getOrderId());
        resultMap.put("status", "200");
        resultMap.put("msg", "success");
        return resultMap;

    }
}
