package com.dou361.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * created by jjdxm on 2015-2-8 下午9:33:35 说明 日期工具，String/long与date相互转换 两个日期的比较等
 * 
 **/
public class DateUtils {

	/** 格式化日期为：yyyy-MM-dd HH:mm:ss */
	public static SimpleDateFormat sdf_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/** 格式化日期为：yyyy-MM-dd HH:mm */
	public static SimpleDateFormat sdf_yyyy_MM_dd_HH_mm = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	/** 格式化日期为：yyyy-MM-dd */
	public static SimpleDateFormat sdf_yyyy_MM_dd = new SimpleDateFormat(
			"yyyy-MM-dd");

	/** 格式化日期为：yyyy-MM */
	public static SimpleDateFormat sdf_yyyy_MM = new SimpleDateFormat("yyyy-MM");
	/** 格式化日期为：MM_dd */
	public static SimpleDateFormat sdf_MM_dd = new SimpleDateFormat("MM月dd日");

	/** 格式化日期为：yyyy */
	public static SimpleDateFormat sdf_yyyy = new SimpleDateFormat("yyyy");
	/** 格式化日期为：MM */
	public static SimpleDateFormat sdf_MM = new SimpleDateFormat("MM");
	/** 格式化日期为：dd */
	public static SimpleDateFormat sdf_dd = new SimpleDateFormat("dd");

	/** 格式化日期为：yyyy/MM/dd HH:mm:ss */
	public static SimpleDateFormat sdf_yyyyxMMxdd_HH_mm_ss = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	/** 格式化日期为：yyyyMMddHHmmss */
	public static SimpleDateFormat sdf_yyyyMMddHHmmss = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	/** 格式化日期为：yyyy.MM.dd */
	public static SimpleDateFormat sdf_yyyydMMddd = new SimpleDateFormat(
			"yyyy.MM.dd");

	/** 格式化日期为：yyyyMMdd */
	public static SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat(
			"yyyyMMdd");
	/** 格式化日期为：yyyyMM */
	public static SimpleDateFormat sdf_yyyyMM = new SimpleDateFormat("yyyyMM");

	/** 格式化日期为：yyyy/MM/dd HH:mm */
	public static SimpleDateFormat sdf_yyyyxMMxdd_HH_mm = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm");

	/** 格式化日期为：yyyy/MM/dd */
	public static SimpleDateFormat sdf_yyyyxMMxdd = new SimpleDateFormat(
			"yyyy/MM/dd");

	/** 格式化日期为：yyyy/MM */
	public static SimpleDateFormat sdf_yyyyxMM = new SimpleDateFormat("yyyy/MM");

	/** 格式化日期为：yyyy年MM月dd日 HH:mm:ss */
	public static SimpleDateFormat sdf_yyyyzMMzdd_HH_mm_ss = new SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm:ss");

	/** 格式化日期为：yyyy年MM月dd日 HH:mm */
	public static SimpleDateFormat sdf_yyyyzMMzdd_HH_mm = new SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm");

	/** 格式化日期为：yyyy年MM月dd日 */
	public static SimpleDateFormat sdf_yyyyzMMzdd = new SimpleDateFormat(
			"yyyy年MM月dd日");

	/** 格式化日期为：yyyy年MM月 */
	public static SimpleDateFormat sdf_yyyyzMM = new SimpleDateFormat(
			"yyyy年MM月");

	/** 格式化日期为：yyyy年MM月dd日 HH时mm分ss秒 */
	public static SimpleDateFormat sdf_yyyyzMMzdd_HHzmmzss = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分ss秒");

	/** 格式化日期为：yyyy年MM月dd日 HH时mm分 */
	public static SimpleDateFormat sdf_yyyyzMMzdd_HHzmm = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分");

	/** 格式化日期为：HH:mm:ss */
	public static SimpleDateFormat sdf_HH_mm_ss = new SimpleDateFormat(
			"HH:mm:ss");
	/** 格式化日期为：HHmmss */
	public static SimpleDateFormat sdf_HHmmss = new SimpleDateFormat("HHmmss");

	/** 格式化日期为：HH:mm */
	public static SimpleDateFormat sdf_HH_mm = new SimpleDateFormat("HH:mm");

	/** 格式化日期为：HH时mm分ss秒 */
	public static SimpleDateFormat sdf_HHzmmzss = new SimpleDateFormat(
			"HH时mm分ss秒");

	/** 格式化日期为：HH时mm分 */
	public static SimpleDateFormat sdf_HHzmm = new SimpleDateFormat("HH时mm分");

	/** 日期转换为字符串 */
	public static String dateToString(SimpleDateFormat sdf, Date date) {
		return sdf.format(date);
	}

	/** 毫秒日期转换为字符串 */
	public static String longDateToString(SimpleDateFormat sdf, long longDate) {
		return sdf.format(new Date(longDate));
	}

	/** 字符串毫秒日期转换为字符串 */
	public static String stringDateToString(SimpleDateFormat sdf,
			String strLongDate) {
		return sdf.format(new Date(Long.valueOf(strLongDate)));
	}

	/** 字符串毫秒日期转换为字符串异常后返回当前时间@param sdf 日期样式@param strDate对应样式字符串的日期@return日期 */
	public static Date stringDateToDate(SimpleDateFormat sdf, String strDate) {

		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/** 字符串毫秒日期转换为字符串异常后返回当前时间的毫秒值 */
	public static long stringDateToLong(SimpleDateFormat sdf, String strDate) {
		try {
			return sdf.parse(strDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date().getTime();
		}
	}

	/** 比较两个日期的大小（-2转换异常0相等1大于-1小于） */
	public static int compareDate(Date dateF, Date dateS) {
		try {
			if (dateF.getTime() > dateS.getTime()) {
				return 1;
			} else if (dateF.getTime() < dateS.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return -2;
		}

	}

	/** 获得指定月的天数 */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/** 获得到今天在当月的天索引 */
	public static int getDayOfMonth() {
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day = aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}

	/** 获得到今天00：00的毫秒值 */
	public static long getToDay() {
		return DateUtils.stringDateToLong(DateUtils.sdf_yyyy_MM_dd,
				DateUtils.dateToString(DateUtils.sdf_yyyy_MM_dd, new Date()));
	}

	/** 获得到一个星期前00：00的毫秒值 */
	public static long getLastWeek() {
		return DateUtils.stringDateToLong(DateUtils.sdf_yyyy_MM_dd,
				DateUtils.dateToString(DateUtils.sdf_yyyy_MM_dd, new Date()))
				- 60000 * 60 * 24 * 7;
	}

	/** 获取指定天的0点 */
	public static long getDayToBeforeDawn(long date) {
		return DateUtils.stringDateToLong(DateUtils.sdf_yyyy_MM_dd,
				DateUtils.longDateToString(DateUtils.sdf_yyyy_MM_dd, date));
	}

	/** 获取指定天的八点 */
	public static long getDayToEight(long date) {
		return DateUtils.stringDateToLong(DateUtils.sdf_yyyy_MM_dd,
				DateUtils.longDateToString(DateUtils.sdf_yyyy_MM_dd, date)) + 60000 * 60 * 8;
	}

	public static String splitDateString(String date) {
		/** 1942年 */
		return date.split(" ")[0];
	}

	/** 获取当天是星期几 */
	public static String getDateToWeek(Date date) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(date);
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		return "星期" + mWay;
	}

	/** 获取当天是日期和星期几 */
	public static String getDateToDateWeek(Date date) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(date);
		String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
		String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
		String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		return mYear + "年" + mMonth + "月" + mDay + "日" + " 星期" + mWay;
	}

	/** 获取到使用时间 小时 */
	public static int getHour(long milliSecondTime) {
		return (int) (milliSecondTime / (60 * 60 * 1000));
	}

	/** 获取使用时间 分钟 */
	public static int getMinute(long milliSecondTime) {
		int hour = (int) (milliSecondTime / (60 * 60 * 1000));
		return (int) ((milliSecondTime - hour * 60 * 60 * 1000) / (60 * 1000));
	}

	/** 获取使用时间 秒钟 */
	public static int getSeconds(long milliSecondTime) {
		int hour = (int) (milliSecondTime / (60 * 60 * 1000));
		int minute = (int) ((milliSecondTime - hour * 60 * 60 * 1000) / (60 * 1000));
		return (int) ((milliSecondTime - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000);
	}

	/** 获取使用时间 秒钟 */
	public static String getStringUseDuration(long milliSecondTime) {
		int hour = (int) (milliSecondTime / (60 * 60 * 1000));
		int minute = (int) ((milliSecondTime - hour * 60 * 60 * 1000) / (60 * 1000));
		int second = (int) ((milliSecondTime - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000);
		return hour + "小时" + minute + "分钟" + second + "秒";
	}
	/** 获取使用时间 分钟和秒 */
	public static String getStringDuration(long milliSecondTime) {
		int minute = (int) ((milliSecondTime) / (60 * 1000));
		int second = (int) ((milliSecondTime - minute * 60 * 1000) / 1000);
		String min = minute+"";
		String sec = second+"";
		if(minute <= 9){
			min = "0"+minute;
		}
		if(second <= 9){
			sec = "0"+second;
		}
		return min + ":" + sec;
	}

}
