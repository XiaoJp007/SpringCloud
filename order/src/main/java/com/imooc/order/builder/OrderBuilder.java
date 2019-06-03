package com.imooc.order.builder;

import com.imooc.order.dto.OrderFrontRequestDto;
import com.imooc.order.dto.OrderRequestDto;
import com.imooc.order.model.OrderDetail;
import com.imooc.order.utils.JsonParser;

public class OrderBuilder {

    /**
     * 订单创建参数构建
     * @param frontRequestDto
     * @return
     */
    public static OrderRequestDto builderOrderInfo(OrderFrontRequestDto frontRequestDto) {

        OrderRequestDto OrderRequestDto = new OrderRequestDto();
        OrderRequestDto.setBuyerName(frontRequestDto.getName());
        OrderRequestDto.setBuyerPhone(frontRequestDto.getPhone());
        OrderRequestDto.setBuyerAddress(frontRequestDto.getAddress());
        OrderRequestDto.setBuyerOpenid(frontRequestDto.getOpenId());
        OrderRequestDto.setOrderList(JsonParser.parserJsonStringToJavaArray(frontRequestDto.getItems(), OrderDetail.class));

        return OrderRequestDto;
    }

}
