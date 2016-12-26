package com.dou361.baseutils.listener;

/**
 * 双击退出
 */
public interface OnDoubleClickListener {

    /**
     * 多击事件的监听
     */
    void onFinishClick();

    /**
     * 没完成点击前触发的点击
     */
    void onContinueClick();
}