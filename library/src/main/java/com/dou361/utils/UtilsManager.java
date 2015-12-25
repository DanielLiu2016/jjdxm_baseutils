package com.dou361.utils;

import android.content.Context;

/**
 * ========================================
 * <p/>
 * 版 权：深圳市晶网科技控股有限公司 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/12/25
 * <p/>
 * 描 述：用户sdk初始化配置
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class UtilsManager {

    private static UtilsManager instance;
    private Context appContext;
    private String userKey;

    private UtilsManager(Context appContext, String userKey) {
        this.appContext = appContext;
        this.userKey = userKey;
    }

    public static UtilsManager getInstance() {
        if (instance == null) {
            throw new RuntimeException("UtilsManager not initialized!");
        } else {
            return instance;
        }
    }

    public static void init(Context appContext, String userKey) {
        instance = new UtilsManager(appContext, userKey);
    }

    public Context getAppContext() {
        return appContext;
    }

    /** 设置开发环境 */
    public void setDebugEnv(boolean flag) {
        LogUtils.setPrintLog(flag);
    }

    /** 设置开发环境 */
    public void setLogLevel(int flag) {
        LogUtils.setLogLevel(flag);
    }

}
