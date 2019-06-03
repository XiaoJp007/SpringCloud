package com.imooc.order.service;

import com.imooc.order.dto.OrderFrontRequestDto;
import com.imooc.order.dto.OrderRequestDto;
import com.imooc.order.dto.OrderResponseDto;
import com.imooc.order.exeception.OrderException;

public interface OrderService {

    /**
     * 创建订单
     *
     * @param requestDto
     */
    OrderResponseDto createOrderInfo(OrderFrontRequestDto requestDto, OrderRequestDto orderRequestDto) throws OrderException;
}
