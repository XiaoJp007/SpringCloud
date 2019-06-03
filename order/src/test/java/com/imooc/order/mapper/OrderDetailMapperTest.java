package com.imooc.order.mapper;

import com.imooc.order.OrderApplicationTests;
import com.imooc.order.model.OrderDetail;
import com.imooc.order.utils.SequenceGenUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@Component
public class OrderDetailMapperTest extends OrderApplicationTests{

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(SequenceGenUtils.getUUID());
        orderDetail.setOrderId(SequenceGenUtils.getUUID());
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductIcon("//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg");
        orderDetail.setCreateTime(new Date());
        OrderDetail result = orderDetailMapper.save(orderDetail);
        Assert.assertTrue(null != result);

    }

}