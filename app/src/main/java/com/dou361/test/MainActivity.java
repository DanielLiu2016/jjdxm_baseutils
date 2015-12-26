package com.dou361.test;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dou361.R;
import com.dou361.utils.CodeUtils;
import com.dou361.utils.LogUtils;
import com.dou361.utils.SPUtils;
import com.dou361.utils.UtilsManager;

public class MainActivity extends AppCompatActivity {

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

    }
}
