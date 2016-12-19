package com.dou361.baseutils.listener;

import android.view.View;

/**
 * 指定时间内多次点击触发单次
 */
public interface OnNoDoubleClickListener {

    /**
     * 指定时间内多次点击触发单次
     */
    void onNoDoubleClick(View v);
}