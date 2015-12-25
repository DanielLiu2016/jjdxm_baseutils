package com.dou361.application;

import android.os.Handler;
import android.os.Looper;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dou361.download.DownloadManager;
import com.dou361.download.DownloadModel;
import com.dou361.download.SqliteManager;
import com.dou361.third_im.Foreground;
import com.dou361.third_im.IMData;
import com.dou361.third_im.SDKHelper;
import com.dou361.third_im.TLSHelper;
import com.jingwang.eluxue_online.activity.FeedBackActivity;
import com.umeng.fb.push.FeedbackPush;

import org.litepal.LitePalApplication;

import java.util.List;

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
 * 创建日期：2015/11/30 11:42
 * <p/>
 * 描 述：全局域
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class BaseApplication extends LitePalApplication {

    /**
     * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
     */
    private static BaseApplication mInstance;
    /**
     * 主线程ID
     */
    private static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;
    /**
     * 下载的数据库管理
     */
    private SqliteManager manager;
    /**
     * volley框架对象
     */
    private RequestQueue mRequestQueue;

    private SDKHelper helper = new SDKHelper();
    private TLSHelper tlsHelper = new TLSHelper();
    private boolean isBackground;

    private IMData imdata;

    @Override
    public void onCreate() {
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;
        super.onCreate();
        /** im数据初始化 */
        imdata = new IMData(this);
        helper.init(this);
        tlsHelper.init(this);
        Foreground.init(this);
        /** 意见反馈初始化 */
        FeedbackPush.getInstance(this).init(FeedBackActivity.class, true);
        /** 添加下载状态列表 */
        manager = SqliteManager.getInstance(getApplicationContext());
        List<DownloadModel> models = manager.getAllDownloadInfo();
        DownloadManager.getInstance(getApplicationContext()).addStateMap(models);
        /** 在应用启动时回调，并且在其他组件被创建之前回调 */
        //Thread.setDefaultUncaughtExceptionHandler(new LogCatUncaughtExceptionHandler());
    }

    /**
     * 获取网络请求队列
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * 资源耗尽的时候回调
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 获得Application实例
     */
    public static BaseApplication getApplication() {
        return mInstance;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public boolean getTestEnvSetting() {
        return imdata.getTestEnvSetting();
    }

    public int getLogLevel() {
        return imdata.getLogLevel();
    }

    public boolean getLogConsole() {
        return imdata.getLogConsole();
    }

}
