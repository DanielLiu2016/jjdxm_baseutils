package com.dou361.http;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/11/11
 * <p/>
 * 描 述：单个Json数据解析，不存在字段时返回默认值int 0，string "",boolean false,object null
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class JsonHelper {

	private JSONObject obj;

	public JsonHelper(JSONObject obj) {
		super();
		this.obj = obj;
	}

	public int getInt(String str) {
		try {
			return obj.getInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public double getDouble(String str) {
		try {
			return obj.getDouble(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public long getLong(String str) {
		try {
			return obj.getLong(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public String getString(String str) {
		try {
			String strObj = obj.getString(str);
			if(strObj == null || "null".equals(strObj)) {
				return "";
			}
			return strObj;
		} catch (Exception e) {
			return "";
		}
	}
	
	public JSONObject getJSONObject(String str) {
		try {
			return obj.getJSONObject(str);
		} catch (Exception e) {
			return obj;
		}
	}
	public JSONArray getJSONArray(String str) {
		try {
			return obj.getJSONArray(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean getBoolean(String str) {
		try {
			return obj.getBoolean(str);
		} catch (Exception e) {
			return false;
		}
	}

}
