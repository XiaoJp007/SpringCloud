package com.imooc.product.controller;

import com.imooc.product.model.ProductCategory;
import com.imooc.product.model.ProductInfo;
import com.imooc.product.service.ProductCategoryService;
import com.imooc.product.service.ProductInfoService;
import com.imooc.product.util.ResultUtil;
import com.imooc.product.vo.ProductCategoryVo;
import com.imooc.product.vo.ProductInfoVo;
import com.imooc.product.vo.ResultVo;
import com.xiao.product.common.DecrProductStockRequestDto;
import com.xiao.product.common.ProductInfoRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/product")
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;


    @RequestMapping("/queryProductList")
    public ResultVo<ProductInfoVo> queryProductList() {

        //查询所有上架的商品
        List<ProductInfo> productList = productInfoService.queryProductInfoListByStatus();

        //从商品列表获取类目type列表
        List<Integer> categoryTypeList = productList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        //从数据库查询类目
        List<ProductCategory> categoryList = productCategoryService.queryCategoryListByCategoryTypeIn(categoryTypeList);

        List<ProductCategoryVo> categoryVoList = new ArrayList<>();
        //构造数据
        for (ProductCategory category : categoryList) {
            ProductCategoryVo categoryVo = new ProductCategoryVo();
            //遍历商品类目集合
            categoryVo.setCategoryCode(category.getCategoryId());
            categoryVo.setCategoryName(category.getCategoryName());


            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productList) {
                if (productInfo.getCategoryType().equals(category.getCategoryType())) {
                    //遍历商品信息集合
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    productInfoVo.setProductName(productInfo.getProductName());
                    productInfoVo.setGetProductIcon(productInfo.getProductIcon());
                    productInfoVo.setProductDesc(productInfo.getProductDescription());
                    productInfoVo.setProductId(productInfo.getProductId());
                    productInfoVo.setProductPrice(productInfo.getProductPrice());
                    //将productInfo的信息拷贝到productInfoVo
//                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            categoryVo.setProductInfoList(productInfoVoList);
            categoryVoList.add(categoryVo);
        }

        ResultVo result = ResultUtil.success(categoryVoList);

        return result;
    }


    /**
     * 通过商品id(是一个list)获取商品列表
     *
     * @return
     */
    @PostMapping("/queryProductListByIds")
    public List<ProductInfoRequestDto> queryProductListByIds(@RequestBody List<String> productIds) {

        return productInfoService.queryProductListByIds(productIds);
    }

    /**
     * 减去订单库存
     * @param cartDtoList
     */
    @PostMapping("/decrProductStock")
    public void decrProductStock(@RequestBody List<DecrProductStockRequestDto> cartDtoList){
        productInfoService.decrProductStock(cartDtoList);

    }
}
