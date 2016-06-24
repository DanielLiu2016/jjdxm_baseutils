package com.dou361.baseutils.utils;

import java.util.HashMap;

/**
 * ========================================
 * <p/>
 * 版 权：jjdxmashl 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/12/26 22:08
 * <p/>
 * 描 述：返回码对应的消息提示
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class CodeUtils {

    private HashMap<Integer, String> codeMap = new HashMap<Integer, String>();
    private String defaultMsg = "";
    private static CodeUtils mCodeUtils;

    private CodeUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 单例模式
     */
    public static CodeUtils getInstance() {
        if (mCodeUtils == null) {
            mCodeUtils = new CodeUtils(true);
        }
        return mCodeUtils;
    }

    public CodeUtils(boolean flag) {
        if (flag) {
            initData();
        }
    }

    /**
     * 初始化返回码
     */
    private void initData() {
        codeMap.put(200, "请求成功!");
        codeMap.put(500, "系统繁忙!");
        codeMap.put(501, "非法参数！");
        codeMap.put(502, "权限或身份失效！");
        codeMap.put(503, "未知请求!");
        codeMap.put(1001, "手机号码已注册，欢迎登录！");
        codeMap.put(1002, "该手机号码还未注册！");
        codeMap.put(1003, "您输入的密码有误！");
        codeMap.put(1004, "注册使用手机号格式不正确!");
        codeMap.put(1005, "验证码不正确，重新获取!");
        codeMap.put(1006, "验证码过期，可重新获取!");
        codeMap.put(1007, "未知请求!");
        codeMap.put(1008, "评分过于频繁，30秒后再评分！");
        codeMap.put(1009, "未知错误！");
        codeMap.put(20000001, "网络连接不可用，请稍后再试！");
        codeMap.put(20000002, "无法连接到服务器，请稍后再试！");
    }

    /**
     * 获取返回码对应的消息
     */
    public String getMessage(int code) {
        String msg = codeMap.get(code);
        if (msg == null) {
            msg = defaultMsg;
        }
        return msg;
    }

}
