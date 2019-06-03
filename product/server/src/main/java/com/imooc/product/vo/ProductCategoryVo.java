package com.imooc.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVo {

    //类目名称 categoryName和返回到前端的name一一对应
    @JsonProperty("name")
    private String categoryName;

    //类目code
    @JsonProperty("code")
    private Integer categoryCode;

    //商品信息list
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoList;


}
