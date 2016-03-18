package com.dou361.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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
 * 创建日期：2015/12/27
 * <p/>
 * 描 述：打开或者关闭软键盘
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class KeyBoardUtils {

    private KeyBoardUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 隐藏软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void hideKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * 设置输入法,如果当前页面输入法打开则关闭
     * @param activity
     */
    public static void hideInputMethod(Activity activity){
        View a = activity.getCurrentFocus();
        if(a != null){
            InputMethodManager imm = (InputMethodManager) UIUtils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 强制显示输入法
     * @param view
     */
    public static void showInputMethod(View view){
        try {
            InputMethodManager imm = (InputMethodManager) UIUtils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 手动打开或关闭软键盘，如果打开就关闭，如果关闭就打开 */
    private static void toggleSoftInput() {
        // 隐藏输入法
        InputMethodManager imm = (InputMethodManager) UIUtils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 显示或者隐藏输入法
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
