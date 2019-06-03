package com.imooc.order.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 订单创建前端参数请求
 */
@Data
public class OrderFrontRequestDto {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "电话不能为空")
    private String phone;

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotBlank(message = "openId不能为空")
    private String openId;

    @NotBlank(message = "购物车不能为空")
    private String items;

}
