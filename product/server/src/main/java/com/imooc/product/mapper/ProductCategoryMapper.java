package com.imooc.product.mapper;

import com.imooc.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryMapper extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> queryCategoryListByCategoryTypeIn(List<Integer> categoryTypeList);
}
