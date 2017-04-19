package com.dou361.jjdxm_baseutils;

import android.app.Application;
import android.os.Handler;

import com.dou361.baseutils.utils.LogType;
import com.dou361.baseutils.utils.LogUtils;
import com.dou361.baseutils.utils.UtilsManager;

/**
 * ========================================
 * <p/>
 * 版 权：dou361 版权所有 （C） 2015
 * <p/>
 * 作 者：chenguanming
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/12/26
 * <p/>
 * 描 述：
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        UtilsManager.getInstance()
                .init(this, "",new Handler(),Thread.currentThread())
                .setFristTag("dou361")
                .setDebugEnv(true)
                .setLogLevel(LogType.LEVEL_ERROR)
                .setCryptKey("this is crypt key!");
    }
}
