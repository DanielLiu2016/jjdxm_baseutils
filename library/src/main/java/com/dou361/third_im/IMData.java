package com.dou361.third_im;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dou361.utils.SPUtils;

public class IMData {
    private String KEY_SETTING_NOTIFICATION = "key_setting_notification";
    private String KEY_SETTING_SOUND = "key_setting_sound";
    private String KEY_SETTING_VIBRATE = "key_setting_vibrate";
    private String KEY_SETTING_TESTENV = "key_setting_testenv";
    private String KEY_SETTING_LOGCONSOLE = "key_setting_logconsole";
    private String KEY_LOG_LEVEL = "key_setting_loglevel";
    private String KEY_USER_NAME = "key_username";
    private String KEY_PASSWORD = "key_pwd";
    private Context context;

    public IMData(Context context) {
        this.context = context;
    }


    public void setSettingNotification(boolean bFlag) {
        SPUtils.spTagName(QQIMParamsConfig.SP_NAME).putData(context, KEY_SETTING_NOTIFICATION, bFlag);
    }


    public boolean getSettingNotification() {
        return (Boolean) SPUtils.spTagName(QQIMParamsConfig.SP_NAME).getData(context, KEY_SETTING_NOTIFICATION, false);
    }

    public void setSettingSound(boolean bFlag) {

        SPUtils.spTagName(QQIMParamsConfig.SP_NAME).putData(context, KEY_SETTING_SOUND, bFlag);
    }

    public boolean getSettingSound() {
        return (Boolean) SPUtils.spTagName(QQIMParamsConfig.SP_NAME).getData(context, KEY_SETTING_SOUND, false);
    }


    public void setSettingVibrate(boolean bFlag) {

        SPUtils.spTagName(QQIMParamsConfig.SP_NAME).putData(context, KEY_SETTING_VIBRATE, bFlag);
    }


    public boolean getSettingVibrate() {
        return (Boolean) SPUtils.spTagName(QQIMParamsConfig.SP_NAME).getData(context, KEY_SETTING_VIBRATE, false);
    }


    public void setUserName(String name) {
        SPUtils.spTagName(QQIMParamsConfig.SP_NAME).putData(context, KEY_USER_NAME, name);
    }


    public String getUserName() {
        return (String) SPUtils.spTagName(QQIMParamsConfig.SP_NAME).getData(context, KEY_USER_NAME, "");
    }


    public void setPassword(String pwd) {
        SPUtils.spTagName(QQIMParamsConfig.SP_NAME).putData(context, KEY_PASSWORD, pwd);
    }

    public String getPassword() {
        return (String) SPUtils.spTagName(QQIMParamsConfig.SP_NAME).getData(context, KEY_PASSWORD, "");
    }


    public void setTestEnvSetting(boolean bFlag) {
        Log.d("imdata", "set testenv:" + bFlag);
        SPUtils.spTagName(QQIMParamsConfig.SP_NAME).putData(context, KEY_SETTING_TESTENV, bFlag);
    }

    public boolean getTestEnvSetting() {
        return (Boolean) SPUtils.spTagName(QQIMParamsConfig.SP_NAME).getData(context, KEY_SETTING_TESTENV, false);
    }

    public void setLogConsole(boolean bFlag) {
        Log.d("imdata", "set testenv:" + bFlag);
        SPUtils.spTagName(QQIMParamsConfig.SP_NAME).putData(context, KEY_SETTING_LOGCONSOLE, bFlag);
    }

    public boolean getLogConsole() {
        return (Boolean) SPUtils.spTagName(QQIMParamsConfig.SP_NAME).getData(context, KEY_SETTING_LOGCONSOLE, true);
    }

    public void setLogLevel(int level) {
        Log.d("imdata", "log level:" + level);
        SPUtils.spTagName(QQIMParamsConfig.SP_NAME).putData(context, KEY_LOG_LEVEL, level);
    }

    public int getLogLevel() {
        //Log.d("imdata","log level:" + (TIMLogLevel) SPUtils.spTagName(QQIMParamsConfig.SP_NAME).getData(KEY_LOG_LEVEL,TIMLogLevel.INFO));
        return (Integer) SPUtils.spTagName(QQIMParamsConfig.SP_NAME).getData(context, KEY_LOG_LEVEL, 4);
    }
}
