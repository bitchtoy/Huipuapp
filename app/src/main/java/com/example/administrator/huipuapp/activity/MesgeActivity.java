package com.example.administrator.huipuapp.activity;

import android.os.Bundle;

import com.example.administrator.huipuapp.R;

/**
 * Created by Administrator on 2017/7/22.
 */

public class MesgeActivity extends  BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesge_view);
        setTitle("流程内容");
        setLeftIcon(R.mipmap.fanhui);
    }
}
