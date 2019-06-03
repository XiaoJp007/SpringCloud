package com.imooc.product.service.impl;

import com.imooc.product.mapper.ProductCategoryMapper;
import com.imooc.product.model.ProductCategory;
import com.imooc.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> queryCategoryListByCategoryTypeIn(List<Integer> categoryTypeList) {

        List<ProductCategory> list = productCategoryMapper.queryCategoryListByCategoryTypeIn(Arrays.asList(11, 12));
        return list;
    }
}
