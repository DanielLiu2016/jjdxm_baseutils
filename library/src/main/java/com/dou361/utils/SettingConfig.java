package com.dou361.utils;


public class SettingConfig {

	/** 非WiFi观看 */
	public static void putNoWifiWatch(boolean flag) {
		SPUtils.putData(UIUtils.getContext(), "NoWifiWatch", flag);
	}

	public static boolean getNoWifiWatch() {
		return (boolean)SPUtils.getData(UIUtils.getContext(), "NoWifiWatch", false);
	}

	/** 非WiFi下载 */
	public static void putNoWifiDown(boolean flag) {
		SPUtils.putData(UIUtils.getContext(), "NoWifiDown", flag);
	}

	public static boolean getNoWifiDown() {
		return (boolean)SPUtils.getData(UIUtils.getContext(), "NoWifiDown", false);
	}

	/** 直播提醒 */
	public static void putDirectNotice(boolean flag) {
		SPUtils.putData(UIUtils.getContext(), "DirectNotice", flag);
	}

	public static boolean getDirectNotice() {
		return (boolean)SPUtils.getData(UIUtils.getContext(), "DirectNotice", false);
	}
	
	/** 是否登陆 */
	public static void putLogin(boolean flag) {
		SPUtils.putData(UIUtils.getContext(), "IsLogin", flag);
	}

	/** 是否登陆 */
	public static boolean getLogin() {
		return (boolean)SPUtils.getData(UIUtils.getContext(), "IsLogin", false);
	}

}
