package com.imooc.product.service.impl;

import com.imooc.product.enums.ProductStatusEnums;
import com.imooc.product.exeception.ExceptionCode;
import com.imooc.product.exeception.OrderException;
import com.imooc.product.mapper.ProductInfoMapper;
import com.imooc.product.model.ProductInfo;
import com.imooc.product.service.ProductInfoService;
import com.xiao.product.common.DecrProductStockRequestDto;
import com.xiao.product.common.ProductInfoRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> queryProductInfoListByStatus() {
        return productInfoMapper.queryProductInfoListByProductStatus(ProductStatusEnums.UP.getCode());
    }


    @Override
    public List<ProductInfoRequestDto> queryProductListByIds(List<String> productIds) {
        //sql返回的类是ProductInfo,而接口返回的类是ProductInfoRequestDto
        List<ProductInfo> productList = productInfoMapper.queryProductListByProductIdIn(productIds);

        if (CollectionUtils.isEmpty(productList)) {
            log.info("商品id{}不存在", productIds);
            throw new OrderException(000, "商品id{}不存在");
        }

        //e表示ProductInfo对象
        return productList.stream().map(e -> {
            ProductInfoRequestDto requestDto = new ProductInfoRequestDto();
            BeanUtils.copyProperties(e, requestDto);
            return requestDto;
        }).collect(Collectors.toList());


    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = OrderException.class)
    public void decrProductStock(List<DecrProductStockRequestDto> cartDtoList) {
        log.info("减去商品库存参数请求cartDtoList{}", cartDtoList);
        if (CollectionUtils.isEmpty(cartDtoList)) {
            log.error("减去商品库存参数请求不合法cartDtoList{}", cartDtoList);
            throw new OrderException(ExceptionCode.QUERY_PARAMS_ERROR.getErrorCode(),
                    ExceptionCode.QUERY_PARAMS_ERROR.getErrorMsg());
        }

        for (DecrProductStockRequestDto cartRequestDto : cartDtoList) {
            //查询当前商品的库存
            Optional<ProductInfo> productInfo = productInfoMapper.findById(cartRequestDto.getProductId());

            //productInfo没有值,相当于是null
            if (!productInfo.isPresent()) {
                log.error("商品id{}不存在", productInfo.get().getProductId());
                throw new OrderException(ExceptionCode.PRODUCT_IS_NULL_ERROR.getErrorCode(),
                        ExceptionCode.PRODUCT_IS_NULL_ERROR.getErrorMsg());
            }

            if (cartRequestDto.getProductQuantity() < 1) {
                log.error("商品id{}的数量productStock{}不合法", productInfo.get().getProductId(), productInfo.get().getProductStock());
                throw new OrderException(ExceptionCode.PRODUCT_QUANTITY_NOT_LEGITIMATE_ERROR.getErrorCode(),
                        ExceptionCode.PRODUCT_QUANTITY_NOT_LEGITIMATE_ERROR.getErrorMsg());
            }

            //得到商品的库存
            int productStock = productInfo.get().getProductStock();

            //剩余商品库存
            int resultProductStock = cartRequestDto.getProductQuantity() - productStock;

            //判断库存是否足够
            if (resultProductStock > 0) {
                log.error("用户购买的商品数量productQuantity{}大于商品库存QuantityStock{}",
                        cartRequestDto.getProductQuantity(), productInfo.get().getProductStock());
                throw new OrderException(ExceptionCode.PRODUCT_STOCK_INSUFFICIENT_ERROR.getErrorCode(),
                        ExceptionCode.PRODUCT_STOCK_INSUFFICIENT_ERROR.getErrorMsg());
            }

            ProductInfo info = productInfo.get();
            info.setProductId(cartRequestDto.getProductId());
            info.setProductStock(resultProductStock);
            productInfoMapper.save(info);

        }

    }


}
