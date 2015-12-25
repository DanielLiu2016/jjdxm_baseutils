package com.dou361.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * created by jjdxm on 2015-4-12 上午2:14:48 说明 检测返回码对应的状态
 **/
public class CheckNetStateUitls {

    /**
     * 检测网络连接状态并提示
     */
    public static boolean checkStatusAndShow(Context context) {
        boolean flag = false;
        if (!NetworkUtils.isNetworkConnected(context)) {
            UIUtils.showToastShort("请检查网络是否连接！");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 检测网络连接状态
     */
    public static boolean checkStatus(Context context) {
        boolean flag = false;
        if (NetworkUtils.isNetworkConnected(context)) {
            flag = true;
        }
        return flag;
    }

}
