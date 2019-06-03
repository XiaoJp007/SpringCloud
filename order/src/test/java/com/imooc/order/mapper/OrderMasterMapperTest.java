package com.imooc.order.mapper;

import com.imooc.order.OrderApplicationTests;
import com.imooc.order.enums.OrderStatusEnums;
import com.imooc.order.model.OrderMaster;
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
public class OrderMasterMapperTest extends OrderApplicationTests{

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Test
    public void saveTest(){
        OrderMaster model = new OrderMaster();

        model.setOrderId(SequenceGenUtils.getUUID());
        model.setBuyerName("我是你大爷1");
        model.setBuyerPhone("13923439057");
        model.setBuyerAddress("龙华新区明治大道1");
        model.setBuyerOpenid(SequenceGenUtils.generateShortUuid());
        model.setOrderAmount(new BigDecimal(20000));
        model.setCreateTime(new Date());
        model.setOrderStatus(OrderStatusEnums.WAIT.getCode());
        model.setPayStatus(0);
        OrderMaster result = orderMasterMapper.save(model);
        Assert.assertTrue(null != result);

    }

}