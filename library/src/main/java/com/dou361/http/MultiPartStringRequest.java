/**
 * Copyright 2013 Mani Selvaraj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dou361.http;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
 * 描 述：文件上传的工具包 使用如下：
 mSingleQueue = Volley.newRequestQueue(this, new MultiPartStack());

 MultiPartStringRequest multiPartRequest = new MultiPartStringRequest(
 Request.Method.POST, SystemConfig.IP + File.separator + SystemConfig.USER_HEAD1, new Listener<String>() {

@Override
public void onResponse(String response) {
mHandler.sendEmptyMessage(StatusConfig.CLOSE_DIALOG);
try {
JSONObject jsonObject = new JSONObject(response);
int code = jsonObject.getInt("Code");
if (code == SystemConfig.Req_Success) {
String url = jsonObject.getString("Data");
UIUtils.showToastShort("修改成功！");
user.setNickName(nick);
if (!"".equals(url)) {
user.setUrl(url);
}
user.setGender(gender);
user.setProvice(province);
user.setCity(city);
user.setCounty(county);
user.update(user.getId());
tv_edit.setEnabled(false);
strCutPath = null;
} else {
UIUtils.showToastShort(CheckCodeUitls.checkCode(code));
tv_edit.setEnabled(true);
}
} catch (JSONException e) {
e.printStackTrace();
UIUtils.showToastShort("未知异常！");
}
LogUtils.tag(tag).log("response String" + response.toString());
}
}, new ErrorListener() {

@Override
public void onErrorResponse(VolleyError error) {
mHandler.sendEmptyMessage(StatusConfig.CLOSE_DIALOG);
UIUtils.showToastShort("修改失败！");
LogUtils.tag(tag).log(error);
}
}) {
@Override
public Map<String, File> getFileUploads() {
Map<String, File> files = new HashMap<String, File>();
if (strCutPath != null) {
files.put("photo", new File(
strCutPath));
}
return files;
}

@Override
public Map<String, String> getStringUploads() {
Map<String, String> params = new HashMap<String, String>();
params.put("action", "UserCentreModify");
params.put("signature", user.getSignature());
params.put("nickName", user.getNickName());
if (gender == 2) {
params.put("sex", 0 + "");
} else {
params.put("sex", gender + "");
}
params.put("province", province);
params.put("city", city);
params.put("county", county);
return params;
}
};
 mSingleQueue.add(multiPartRequest);

 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class MultiPartStringRequest extends Request<String> implements MultiPartRequest{

	private final Listener<String> mListener;
	/* To hold the parameter name and the File to upload */
	private Map<String,File> fileUploads = new HashMap<String,File>();
	
	/* To hold the parameter name and the string content to upload */
	private Map<String,String> stringUploads = new HashMap<String,String>();
	
    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MultiPartStringRequest(int method, String url, Listener<String> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }



    public void addFileUpload(String param,File file) {
    	fileUploads.put(param,file);
    }
    
    public void addStringUpload(String param,String content) {
    	stringUploads.put(param,content);
    }
    
    /**
     * 要上传的文件
     */
    public Map<String,File> getFileUploads() {
    	return fileUploads;
    }
    
    /**
     * 要上传的参数
     */
    public Map<String,String> getStringUploads() {
    	return stringUploads;
    }
    

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

	@Override
	protected void deliverResponse(String response) {
		if(mListener != null) {
			mListener.onResponse(response);
		}
	}
	
	/**
	 * 空表示不上传
	 */
    public String getBodyContentType() {
        return null;
    }
}