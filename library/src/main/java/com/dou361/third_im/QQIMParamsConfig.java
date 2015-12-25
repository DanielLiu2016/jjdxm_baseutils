package com.dou361.third_im;

import android.os.Environment;

import com.dou361.utils.FileUtils;

/**
 * ========================================
 * <p/>
 * 版 权：深圳市晶网科技控股有限公司 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/11/25
 * <p/>
 * 描 述：QQ即时通讯参数常量等配置说明
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class QQIMParamsConfig {

    public static final String NEW_FRIENDS_USERNAME = "system_new_friends";
    public static final String NEW_FRIENDS_NICK = "新的朋友";
    public static final String SYSTEM_TIPS_USERNAME = "system_system_tips";
    public static final String SYSTEM_TIPS_NICK = "系统消息";
    public static final String PUBLIC_GROUP_USERNAME = "system_public_group_container";
    public static final String PUBLIC_GROUP_NICK = "公开群";
    public static final String PRIVATE_GROUP_USERNAME = "system_private_group_container";
    public static final String PRIVATE_GROUP_NICK = "讨论组";
    public static final String CHAT_ROOM_USERNAME = "system_chat_room_container";
    public static final String CHAT_ROOM_NICK = "聊天室";

    public static final String TYPE_PRIVATE_GROUP = "Private";
    public static final String TYPE_PUBLIC_GROUP = "Public";
    public static final String TYPE_CHAT_ROOM = "ChatRoom";

    public static final String INVITE_FRIEND_TO_GROUP = "system_invite_friend_to_group";
    public static final String DELETE_GROUP_MEMBER = "system_delete_group_member";

    public static final int RECENT_MSG_NUM = 500;
//		public static final int ACCOUNT_TYPE = 792;
//		public static final int SDK_APPID = 1400001533;

    public static final int ACCOUNT_TYPE = 670;
    public static final int SDK_APPID = 1400001314;

    public static final int MAX_GROUP_NAME_LENGTH = 30;
    public static final int MAX_GROUP_INTRODUCE_LENGTH = 300;
    public static final int MAX_GROUP_MEMBER_NUM = 2000;
    public static final int MAX_TEXT_MSG_LENGTH = 1 * 1024;
    public static final int MAX_USER_NICK_NAME = 64;

    public static final int TEXT_MSG_FAILED_FOR_TOO_LOGNG = 85;
    public static final int SEND_MSG_FAILED_FOR_PEER_NOT_LOGIN = 6011;
    public static final int MIN_VOICE_RECORD_TIME = 1000;
    public static final int MAX_SEND_FILE_LEN = 28 * 1024 * 1024;

    public static final String myDEMO_NEW_MESSAGE = "com.example.mydemo.new_message";
    public final static String BASE_URL = "http://203.195.198.121/index.php/Home/User/";
    public final static String BASE_URL_LIST = "http://203.195.198.121/index.php/Home/List/";
    public static String TH_IMG_CACHE_DIR = FileUtils.getIconDir() + "/TH_IMG/";
    public static String ORG_IMG_CACHE_DIR = FileUtils.getIconDir() + "/ORG_IMG/";
    public static String FILE_DIR = Environment.getExternalStorageDirectory() + "/tencent/IMSDK_DEMO/FILE/";
    public static String IMAG_DIR = Environment.getExternalStorageDirectory() + "/tencent/IMSDK_DEMO/IMG/";
    public static final int THUMB_IMG_MAX_HEIGHT = 198;
    public static final int THUMB_IMG_MAX_WIDTH = 66;

    public static final String SP_NAME = "im_simple_data";
    public final static long DIS_INTERVAL = 300;


}
