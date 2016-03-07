package com.dou361.test;

import android.app.Application;
import android.os.Handler;

import com.dou361.utils.LogUtils;
import com.dou361.utils.UtilsManager;

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
        UtilsManager.init(this, "",new Handler(),Thread.currentThread());
        UtilsManager.getInstance().setDebugEnv(true);
        UtilsManager.getInstance().setLogLevel(LogUtils.LogType.LEVEL_ERROR);
    }
}
