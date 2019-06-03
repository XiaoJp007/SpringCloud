package com.imooc.order.exeception;

public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(int code, String message) {
        super(message);
        this.code = code;
    }


}
