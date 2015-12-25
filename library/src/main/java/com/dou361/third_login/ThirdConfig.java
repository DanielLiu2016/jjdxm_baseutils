package com.dou361.third_login;

public class ThirdConfig {

	/** qq的APPID */
	public static final String QQ_APPID = "1104891320";
	/** 获取内容 */
	public static final String QQ_SCOPE = "get_user_info";

	public static final String Weibo_APPID = "4272095289";
	/**
	 * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
	 * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
	 */
	public static final String Weibo_REDIRECT_URL = "http://www.sina.com";

	/**
	 * WeiboSDKDemo 应用对应的权限，第三方开发者一般不需要这么多，可直接设置成空即可。 详情请查看 Demo 中对应的注释。
	 */
	public static final String Weibo_SCOPE = "all";

}
