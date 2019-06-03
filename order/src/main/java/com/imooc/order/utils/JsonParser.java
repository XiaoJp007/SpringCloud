/*******************************************************************************
 * Project Key : CPPII
 * Create on 2013-10-11 下午1:38:24
 * Copyright (c) 2008 - 2011.深圳市腾云在线科技控股有限公司版权所有. 
 * 注意：本内容仅限于深圳市腾云在线科技控股有限公司内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.imooc.order.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.*;

/**
 * <P>Json格式解析工具类</P>
 *
 * @author 林仙龙（15361632946） 2013-10-11 下午1:38:24
 * @version 1.0
 */

@Slf4j
public class JsonParser {

    private JsonParser() {
    }

    /**
     * <p>转换json字符串为java object</p>
     *
     * @param jsonDetailsResult
     * @param cls
     * @return
     * @author 林仙龙（15361632946） 2013-10-11 下午1:39:42
     */
    public static <T> T parserJsonStringToJavaObject(final String jsonDetailsResult, final Class<T> cls) {
        return JSON.toJavaObject(JSON.parseObject(jsonDetailsResult), cls);
    }

    /**
     * <p>转换json字符串为java object</p>
     *
     * @param jsonDetailsResult
     * @param cls
     * @return
     * @author 林仙龙（15361632946） 2013-10-11 下午1:39:42
     */
    public static <T> List<T> parserJsonStringToJavaArray(final String jsonDetailsResult, final Class<T> cls) {
        final List<T> javaOjbects = new ArrayList<T>();
        final JSONArray array = JSON.parseArray(jsonDetailsResult);
        for (int i = 0; i < array.size(); i++) {
            javaOjbects.add(JSON.toJavaObject(JSON.parseArray(jsonDetailsResult).getJSONObject(i), cls));
        }
        return javaOjbects;
    }

    /**
     * <p>转换java object为json 字符串</p>
     *
     * @return
     * @author 林仙龙（15361632946） 2013-10-11 下午1:39:42
     */
    public static <T> String parserJavaObjectToJsonString(final T javaObject) {
        return JSON.toJSONString(javaObject, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * <p>list转json</p>
     *
     * @param javaList
     * @return
     * @author 刘武 2018-8-10 上午10:21:05
     */
    public static <T> String parserJavaListToJsonString(final T javaList) {
        return JSONArray.toJSONString(javaList, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * <p>TODO</p>
     *
     * @param javaObject
     * @return
     * @author 林仙龙（15361632946） 2014-7-9 下午1:38:36
     */
    public static <T> String parserJavaObjectToJsonString(final T javaObject, boolean isNullToEmpty) {
        if (isNullToEmpty)
            return JSON.toJSONString(javaObject, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat,
                    SerializerFeature.WriteNullStringAsEmpty);
        else
            return JSON.toJSONString(javaObject, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * <p>json字符串转map</p>
     *
     * @param jsonDetailsResult json字符串
     * @return
     * @throws Exception
     * @author 林仙龙 2017-3-3 下午12:26:23
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parserJsonToMap(final String jsonDetailsResult) throws Exception {
        Assert.notNull(jsonDetailsResult);
        return JSONObject.parseObject(jsonDetailsResult, Map.class);
    }

    /**
     * <p>json字符串转map对象</p>
     *
     * @param jsonDetailsResult json字符串
     * @return
     * @throws Exception
     * @author 叶新东 2017-5-17 下午12:26:23
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parserJsonToMapObject(final String jsonDetailsResult) throws Exception {
        Assert.notNull(jsonDetailsResult);
        return JSONObject.parseObject(jsonDetailsResult, Map.class);
    }

    /**
     * <p>json字符串转LinkedHashMap对象(有序map)</p>
     *
     * @param jsonDetailsResult json字符串
     * @return
     * @throws Exception
     * @author 叶新东 2017-12-22 下午12:26:23
     */
    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String, Object> parserJsonToLinkedHashMapObject(final String jsonDetailsResult)
            throws Exception {
        Assert.notNull(jsonDetailsResult);
        return JSONObject.parseObject(jsonDetailsResult, LinkedHashMap.class);
    }

    /**
     * <p>json字符串转list套map集合</p>
     *
     * @param jsonDetailsResult
     * @return
     * @throws Exception
     * @author 叶新东（18124509759） 2017-3-4 下午12:34:00
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> parserJsonToMapList(final String jsonDetailsResult) throws Exception {
        Assert.notNull(jsonDetailsResult);
        return JSONObject.parseObject(jsonDetailsResult, List.class);
    }

    public static void main(String[] args) {
        System.out.println(parserJavaObjectToJsonString(new Date(), true));
    }

}
