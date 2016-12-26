package com.dou361.baseutils.listener;

/**
 * 多击事件的监听
 */
public interface OnMultiClickListener {

    /**
     * 没完成点击前触发的点击
     *
     * @param remainTimes 还差多少次
     * @param sumTimes    总次数
     */
    void onContinueClick(int remainTimes, int sumTimes);

    /**
     * 多击事件的监听
     */
    void onFinishClick();

}