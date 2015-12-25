package com.dou361.http;

import java.io.File;
import java.util.Map;
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
 * 描 述：文件上传的工具包
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public interface MultiPartRequest {

    /** 上传的文件对象 */
    public void addFileUpload(String param, File file);

    /** 参数 */
    public void addStringUpload(String param, String content);

    /** 上传的文件对象集合 */
    public Map<String,File> getFileUploads();

    /** 上传的参数集合 */
    public Map<String,String> getStringUploads();
}