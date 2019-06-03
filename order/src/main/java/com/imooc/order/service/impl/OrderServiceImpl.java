package com.imooc.order.service.impl;

import com.imooc.order.client.ProductClient;
import com.imooc.order.dto.*;
import com.imooc.order.enums.ExceptionCode;
import com.imooc.order.enums.OrderStatusEnums;
import com.imooc.order.exeception.OrderException;
import com.imooc.order.mapper.OrderDetailMapper;
import com.imooc.order.mapper.OrderMasterMapper;
import com.imooc.order.model.OrderDetail;
import com.imooc.order.model.OrderMaster;
import com.imooc.order.service.OrderService;
import com.imooc.order.utils.DecimalUtil;
import com.imooc.order.utils.JsonParser;
import com.imooc.order.utils.SequenceGenUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = OrderException.class)
    public OrderResponseDto createOrderInfo(OrderFrontRequestDto requestDto, OrderRequestDto orderRequestDto) throws OrderException {
        log.info("订单创建参数请求{}", requestDto);
        OrderResponseDto responseDto = new OrderResponseDto();

        String orderId = SequenceGenUtils.getUniqueKey();
        try {

            //查询商品信息
            List<CartDto> cartDtoList = JsonParser.parserJsonStringToJavaArray(requestDto.getItems(), CartDto.class);

            List<String> productIdList = new ArrayList<>();

            for (CartDto cartDto : cartDtoList) {
                String productId = cartDto.getProductId();
                productIdList.add(productId);
            }

            List<ProductInfoRequestDto> productInfoList = productClient.queryProductListByIds(productIdList);

            //计算商品的金额 数量 * 单价
            BigDecimal orderTotalAmount = BigDecimal.ZERO;
            for (ProductInfoRequestDto productInfo : productInfoList) {
                for (CartDto cartDto : cartDtoList) {
                    if (productInfo.getProductId().equals(cartDto.getProductId())) {
                        BigDecimal orderAmount = DecimalUtil.multiply(new BigDecimal(cartDto.getProductQuantity()), productInfo.getProductPrice());
                        orderTotalAmount = DecimalUtil.add(orderTotalAmount, orderAmount);
                        BeanUtils.copyProperties(orderRequestDto, productInfo);

                        //保存商品详情
                        OrderDetail orderDetail = new OrderDetail();
                        BeanUtils.copyProperties(productInfo, orderDetail);
                        orderDetail.setDetailId(SequenceGenUtils.getUUID());
                        orderDetail.setOrderId(orderId);
                        orderDetail.setCreateTime(new Date());
                        orderDetail.setProductPrice(orderAmount);
                        orderDetail.setProductQuantity(cartDto.getProductQuantity());
                        orderDetailMapper.save(orderDetail);
                    }

                }

            }
            //扣库存
            log.info("扣除商品库存开始");
            productClient.decreaseProductStock(cartDtoList);

            log.info("商品库存扣除成功,开始创建订单", orderRequestDto.getOrderId());
            BeanUtils.copyProperties(requestDto, orderRequestDto);
            orderRequestDto.setOrderId(orderId);
            orderRequestDto.setOrderAmount(orderTotalAmount);
            orderRequestDto.setCreateTime(new Date());
            orderRequestDto.setOrderStatus(1);
            orderRequestDto.setPayStatus(1);
            OrderMaster orderMaster = new OrderMaster();
            BeanUtils.copyProperties(orderRequestDto, orderMaster);
            orderMasterMapper.save(orderMaster);
        } catch (Exception e) {
            if (e instanceof OrderException) {
                log.error("订单创建异常{}", e.getMessage());
                throw new OrderException(000000, e.getMessage());
            } else {
                log.error("订单创建异常{}", e);
                throw new OrderException(ExceptionCode.CREATE_ORDER_ERROR.getErrorCode(), ExceptionCode.CREATE_ORDER_ERROR.getErrorMsg());
            }
        }

        responseDto.setOrderId(orderId);
        log.info("订单orderId{}创建成功", responseDto.getOrderId());
        return responseDto;

    }
}
