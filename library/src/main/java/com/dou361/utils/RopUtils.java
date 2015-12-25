package com.dou361.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * ========================================
 * <p/>
 * 版 权：jjdxmashl 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/12/5 16:30
 * <p/>
 * 描 述：加密签名URL
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class RopUtils {

    /**
     * 默认签名
     *
     * @param paramValues 需要签名的参数列表
     * @return 签名
     */
    public static String sign(Map<String, String> paramValues) {
        return sign(paramValues, null, "");
    }

    /**
     * 签名
     *
     * @param paramValues      参数列表
     * @param ignoreParamNames 忽略签名的列表
     * @return 签名
     */
    public static String sign(Map<String, String> paramValues, List<String> ignoreParamNames) {
        return sign(paramValues, null, "");
    }

    /**
     * 进行签名
     *
     * @param paramValues 需要签名的参数列表
     * @param secret      签名参数key
     * @return 签名
     */
    public static String sign(Map<String, String> paramValues, String secret) {
        return sign(paramValues, null, secret);
    }

    /**
     * 签名
     *
     * @param paramValues      参数列表
     * @param ignoreParamNames 忽略签名的参数列表
     * @param secret           签名参数
     * @return 签名
     */
    public static String sign(Map<String, String> paramValues,
                              List<String> ignoreParamNames, String secret) {
        if (paramValues == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        List<String> paramNames = new ArrayList<String>(paramValues.size());
        paramNames.addAll(paramValues.keySet());
        if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
            for (String ignoreParamName : ignoreParamNames) {
                paramNames.remove(ignoreParamName);
            }
        }
        Collections.sort(paramNames);
        sb.append(secret);
        for (String paramName : paramNames) {
            sb.append(paramName).append(paramValues.get(paramName));
        }
        sb.append(secret);
        return MD5Utils.getMD5Standard(sb.toString());
    }

}
