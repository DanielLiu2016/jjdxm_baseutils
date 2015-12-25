package com.dou361.bdConfig;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dou361.note.view.ViewUtils;
import com.dou361.note.view.annotation.ViewInject;
import com.dou361.ui.StickEditText;
import com.dou361.utils.SPUtils;
import com.jingwang.eluxue_online.R;
import com.jingwang.eluxue_online.activity.BaseActivity;
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
 * 创建日期：2015/11/30 11:45
 * <p/>
 * 描 述：常量配置
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class BDActivity extends BaseActivity implements OnClickListener, StickEditText.OnSendListener {

    /** 返回  */
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;
    /** 应用访问的IP  */
    @ViewInject(R.id.ll_ip)
    private LinearLayout ll_ip;
    /** 应用访问的IP的后缀  */
    @ViewInject(R.id.ll_preName)
    private LinearLayout ll_preName;
    /**  IP地址 */
    @ViewInject(R.id.tv_ip)
    private TextView tv_ip;
    /**  IP地址后缀 */
    @ViewInject(R.id.tv_preName)
    private TextView tv_preName;
    /** 返回  */
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    /** 标题  */
    @ViewInject(R.id.tv_center)
    private TextView tv_center;
    /**  上下文 */
    private Context context;
    private Activity activity;
    /**  编辑文本框 */
    private StickEditText mStickEditText;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_bd);
        ViewUtils.inject(this);
        context = this;
        activity = this;
        tv_title.setText("返回");
        tv_center.setText("常量配置");
        ll_back.setOnClickListener(this);
        ll_ip.setOnClickListener(this);
        ll_preName.setOnClickListener(this);
        tv_ip.setText((String) SPUtils.getData(this, "configip", ""));
        tv_preName.setText((String) SPUtils.getData(this, "configpreName", ""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                onBackPressed();
                break;
            case R.id.ll_ip:
                showEditTextDialog(tv_ip);
                break;
            case R.id.ll_preName:
                showEditTextDialog(tv_preName);
                break;
        }
    }

    /**
     * 显示popuwindow
     */
    private void showEditTextDialog(TextView v) {
        mStickEditText = new StickEditText(activity);
        /** 设置layout在PopupWindow中显示的位置 */
        mStickEditText.setAnimationStyle(R.style.Animations_GrowFromBottom);
        mStickEditText.showAsDropDown(ll_preName, 0, 0);
        mStickEditText.setOnSendListener(this, v);
    }


    @Override
    public void updateData(String trim, View v) {
        switch (v.getId()) {
            case R.id.tv_ip:
                tv_ip.setText(trim);
                SPUtils.getData(context, "configip", trim);
                mStickEditText.dismiss();
                break;
            case R.id.tv_preName:
                tv_preName.setText(trim);
                SPUtils.putData(context, "configpreName", trim);
                mStickEditText.dismiss();
                break;
        }
    }
}
