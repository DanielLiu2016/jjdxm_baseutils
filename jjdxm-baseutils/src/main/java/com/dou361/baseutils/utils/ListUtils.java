package com.dou361.baseutils.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * created by jjdxm on 2015-2-8 下午9:38:59 说明 list集合的方法
 **/
public class ListUtils {

	private ListUtils(){
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/** 判断集合是否为空 */
	public static boolean isEmpty(List<Object> list) {

		if (list == null) {
			return true;
		}
		return false;

	}

	/** 判断集合是否为空 */
	public static <T> int size(List<T> mDatas) {

		if (mDatas == null) {
			return 0;
		}
		return mDatas.size();

	}

	/** 按照排序字段进行排序 */
	public static void sortIntegerList(List<Integer> list) {
		if (list != null) {
			Comparator comp = new Comparator() {
				public int compare(Object o1, Object o2) {
					int r1 = (Integer) o1;
					int r2 = (Integer) o2;
					return r1 - r2;
				}
			};
			Collections.sort(list, comp);
		}
	}

	/** 将集合按字符串输出 */
	public static String listToString(List<Integer> ids) {
		if (ids == null || ids.size() <= 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ids.size(); i++) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(ids.get(i));
		}
		return sb.toString();
	}


}
