package com.imooc.order.enums;


import lombok.Getter;

@Getter
public enum OrderStatusEnums {

    WAIT(0, "新下单"),

    DOWN(0, "下架"),;

    private Integer code;

    private String message;


    OrderStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
