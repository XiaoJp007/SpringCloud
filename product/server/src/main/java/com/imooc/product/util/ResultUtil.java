package com.imooc.product.util;

import com.imooc.product.vo.ResultVo;

public class ResultUtil {

    public static ResultVo success(Object object) {
        ResultVo result = new ResultVo();
        result.setData(object);
        result.setCode(0);
        result.setMsg("success");

        return result;
    }
}
