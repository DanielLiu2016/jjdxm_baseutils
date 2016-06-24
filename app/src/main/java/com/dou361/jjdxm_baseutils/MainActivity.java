package com.dou361.jjdxm_baseutils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dou361.baseutils.utils.CodeUtils;
import com.dou361.baseutils.utils.DateUtils;
import com.dou361.baseutils.utils.LogUtils;
import com.dou361.baseutils.utils.SPUtils;

import java.util.Date;

public class MainActivity extends Activity {

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
        SPUtils.putData(this, "ceshi", "1992");
        SPUtils.spTagName("ming").putData(this, "ceshi", "1992");
        CodeUtils.getInstance().getMessage(200);
        DateUtils.getData(DateUtils.DateType.sdf_yyyy_MM_dd, new Date());

    }
}
