package com.dou361.bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * ========================================
 * <p/>
 * 版 权：深圳市晶网电子科技有限公司 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/11/11
 * <p/>
 * 描 述：版本更新的Bean对象
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class Update implements Serializable {

    /**
     * 版本号
     */
    private int versionCode;
    /**
     * 版本名
     */
    private String versionName;
    /**
     * apk下载地址
     */
    private String downloadUrl;
    /**
     * 更新日志
     */
    private String updateLog;

    public Update(JSONObject jObj) throws JSONException {
        if (jObj == null) {
            return;
        }
        if (!jObj.isNull("versionCode")) {
            versionCode = jObj.getInt("versionCode");
        }
        if (!jObj.isNull("versionName")) {
            versionName = jObj.getString("versionName");
        }
        if (!jObj.isNull("downloadUrl")) {
            downloadUrl = jObj.getString("downloadUrl");
        }
        if (!jObj.isNull("updateLog")) {
            updateLog = jObj.getString("updateLog");
        }
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }
}
