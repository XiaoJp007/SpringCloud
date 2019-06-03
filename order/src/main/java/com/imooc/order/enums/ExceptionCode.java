package com.imooc.order.enums;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    QUERY_PARAMS_ERROR(000001,"请求参数异常"),

    CREATE_ORDER_ERROR(000002,"创建订单异常")

    ;


    private Integer errorCode;

    private String errorMsg;

    ExceptionCode(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
