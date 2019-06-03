package com.imooc.product.vo;


import lombok.Data;

/**
 * http请求返回对象
 * @param <T>
 */
@Data
public class ResultVo<T> {

    //错误/正确的状态吗
    private Integer code;

    //返回的操作信息成功/失败
    private String msg;

    //返回的具体数据内容
    private T data;
}
