package com.dou361.http;

import com.dou361.utils.FileUtils;
import com.dou361.utils.LogUtils;
import com.dou361.utils.StringUtils;
import com.jingwang.eluxue_online.utils.SystemConfig;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
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
 * 描 述：联网帮助类
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class HttpHelper {
	/** get请求，获取返回字符串内容 */
	public static HttpResult get(String url) {
		HttpGet httpGet = new HttpGet(url);
		return execute(url, httpGet);
	}
	/** get请求，获取返回字符串内容 */
	public static HttpResult getXml(String url) {
		HttpGet httpGet = new HttpGet(url);
		return execute(url, httpGet);
	}

	/** post请求，获取返回字符串内容 */
	public static HttpResult post(String url, byte[] bytes) {
		HttpPost httpPost = new HttpPost(url);
		ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bytes);
		httpPost.setEntity(byteArrayEntity);
		return execute(url, httpPost);
	}
	/** post请求，获取返回字符串内容 
	 * @param list */
	public static HttpResult postXml(String url, List<NameValuePair> list) {
		try {
			HttpPost httpPost = new HttpPost(url);
				HttpEntity httpentity = new UrlEncodedFormEntity(list,
						"UTF-8");
				httpPost.setEntity(httpentity);
				return execute(url, httpPost);
			} catch (UnsupportedEncodingException e) {
				LogUtils.log(e);
				return null;
			}
	}

	/** post请求，获取返回字符串内容 */
	public static HttpResult post(String url,List<NameValuePair> list) {

		try {
		HttpPost httpPost = new HttpPost(url);
			if(url.contains(SystemConfig.USER_HEAD1)){
			httpPost.addHeader("Content-Type", "multipart/form-data");}
			HttpEntity httpentity = new UrlEncodedFormEntity(list,
					"UTF-8");
			httpPost.setEntity(httpentity);
			return execute(url, httpPost);
		} catch (UnsupportedEncodingException e) {
			LogUtils.log(e);
			return null;
		}
	}
	
	/** 下载 */
	public static HttpResult download(String url) {
		HttpGet httpGet = new HttpGet(url);
		return execute(url, httpGet);
	}

	/** 执行网络访问 */
	private static HttpResult execute(String url, HttpRequestBase requestBase) {
		boolean isHttps = url.startsWith("https://");// 判断是否需要采用https
		AbstractHttpClient httpClient = HttpClientFactory.create(isHttps);
		HttpContext httpContext = new SyncBasicHttpContext(
				new BasicHttpContext());
		HttpRequestRetryHandler retryHandler = httpClient
				.getHttpRequestRetryHandler();// 获取重试机制
		int retryCount = 0;
		boolean retry = true;
		while (retry) {
			try {
				HttpResponse response = httpClient.execute(requestBase,
						httpContext);// 访问网络
				if (response != null) {
					return new HttpResult(response, httpClient, requestBase);
				}
			} catch (Exception e) {
				IOException ioException = new IOException(e.getMessage());
				retry = retryHandler.retryRequest(ioException, ++retryCount,
						httpContext);// 把错误异常交给重试机制，以判断是否需要采取从事
				LogUtils.log(e);
			}
		}
		return null;
	}

	/** http的返回结果的封装，可以直接从中获取返回的字符串或者流 */
	public static class HttpResult {
		private HttpResponse mResponse;
		private InputStream mIn;
		private String mStr;
		private HttpClient mHttpClient;
		private HttpRequestBase mRequestBase;

		public HttpResult(HttpResponse response, HttpClient httpClient,
				HttpRequestBase requestBase) {
			mResponse = response;
			mHttpClient = httpClient;
			mRequestBase = requestBase;
		}

		public int getCode() {
			StatusLine status = mResponse.getStatusLine();
			return status.getStatusCode();
		}

		/** 从结果中获取字符串，一旦获取，会自动关流，并且把字符串保存，方便下次获取 */
		public String getString() {
			if (!StringUtils.isEmpty(mStr)) {
				return mStr;
			}
			InputStream inputStream = getInputStream();
			ByteArrayOutputStream out = null;
			if (inputStream != null) {
				try {
					out = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024 * 4];
					int len = -1;
					while ((len = inputStream.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					byte[] data = out.toByteArray();
					mStr = new String(data, "utf-8");
				} catch (Exception e) {
					LogUtils.log(e);
				} finally {
					FileUtils.close(out);
					close();
				}
			}
			return mStr;
		}

		/** 获取流，需要使用完毕后调用close方法关闭网络连接 */
		public InputStream getInputStream() {
			if (mIn == null && getCode() < 300) {
				HttpEntity entity = mResponse.getEntity();
				try {
					mIn = entity.getContent();
				} catch (Exception e) {
					LogUtils.log(e);
				}
			}
			return mIn;
		}

		/** 关闭网络连接 */
		public void close() {
			if (mRequestBase != null) {
				mRequestBase.abort();
			}
			FileUtils.close(mIn);
			if (mHttpClient != null) {
				mHttpClient.getConnectionManager().closeExpiredConnections();
			}
		}
	}
}
