package com.dou361.utils;

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


}
