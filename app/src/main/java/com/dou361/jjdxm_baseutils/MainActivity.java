package com.dou361.jjdxm_baseutils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dou361.baseutils.utils.CodeUtils;
import com.dou361.baseutils.utils.DateUtils;
import com.dou361.baseutils.utils.LogUtils;
import com.dou361.baseutils.utils.SPUtils;
import com.dou361.baseutils.utils.StringUtils;

import java.util.Date;

public class MainActivity extends Activity {

    private EditText et_number;
    private TextView tv_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.logTagName("mainActivity").log("--------111111-------");
                LogUtils.log("--------111111-------");
            }
        });
        et_number = (EditText) findViewById(R.id.et_number);
        tv_number = (TextView) findViewById(R.id.tv_number);
        findViewById(R.id.btn_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_number.setText(StringUtils.formatInclueIntNumber(Double.valueOf(et_number.getText().toString())));
            }
        });
        SPUtils.putData(this, "ceshi", "1992");
        SPUtils.spTagName("ming").putData(this, "ceshi", "1992");
        CodeUtils.getInstance().getMessage(200);
        DateUtils.getData(DateUtils.DateType.sdf_yyyy_MM_dd, new Date());

    }
}
