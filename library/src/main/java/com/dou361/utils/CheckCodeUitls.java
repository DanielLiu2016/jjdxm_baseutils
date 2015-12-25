package com.dou361.utils;

import java.lang.reflect.Field;
/**
 * created by jjdxm on 2015-4-12 上午2:14:48 说明 检测返回码对应的状态
 **/
public class CheckCodeUitls {
	
	public static String checkCode(int code) {
		CodeStatus cc = new CodeStatus();
		Field[] fields = CodeStatus.class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getType() == String.class) {
				fields[i].setAccessible(true);
				if (fields[i].getName().endsWith("jjdxm" + code)) {
					try {
						return fields[i].get(cc).toString();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return cc.defaultMsg;
	}

}
