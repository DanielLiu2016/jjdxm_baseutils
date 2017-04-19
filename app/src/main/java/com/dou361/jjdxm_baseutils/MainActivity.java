package com.dou361.jjdxm_baseutils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.baseutils.listener.OnDoubleClickListener;
import com.dou361.baseutils.listener.OnMultiClickListener;
import com.dou361.baseutils.utils.DateType;
import com.dou361.baseutils.utils.DateUtils;
import com.dou361.baseutils.utils.SPUtils;
import com.dou361.baseutils.utils.StringUtils;
import com.dou361.baseutils.utils.UIUtils;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：chenguanming
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2017/4/19 23:23
 * <p>
 * 描 述：
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class MainActivity extends Activity {

    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.tv_number)
    TextView tvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SPUtils.putData("ceshi", "1992");
        SPUtils.spTagName("ming").putData("ceshi", "1992");
        DateUtils.getDate(DateType.sdf_yyyy_MM_dd, new Date());

    }

    @OnClick({R.id.btn_double, R.id.btn_mulit, R.id.btn_nodouble, R.id.btn, R.id.btn_number})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_double:
                UIUtils.setOnDoubleClickListener(2000, new OnDoubleClickListener() {
                    @Override
                    public void onContinueClick() {
                        showToast("再点击一次退出");
                    }

                    @Override
                    public void onFinishClick() {
                        showToast("onFinishClick");
                    }
                });
                break;
            case R.id.btn_mulit:
                UIUtils.setOnMultiClickListener(5, new OnMultiClickListener() {
                    @Override
                    public void onContinueClick(int remainTimes, int sumTimes) {
                        showToast("还要点击" + remainTimes + "次进入特殊模式");
                    }

                    @Override
                    public void onFinishClick() {
                        showToast("onFinishClick");
                    }
                });
                break;
            case R.id.btn_nodouble:
                if (UIUtils.isNoFastDoubleClick(2000)) {
                    showToast("onFinishClick");
                }
                break;
            case R.id.btn:
                break;
            case R.id.btn_number:
                tvNumber.setText(StringUtils.formatInclueIntNumber(Double.valueOf(etNumber.getText().toString())));
                break;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
