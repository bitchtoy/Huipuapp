package com.example.administrator.huipuapp.activity;

import android.os.Bundle;

import com.example.administrator.huipuapp.R;

/**
 * Created by Administrator on 2017/7/25.
 */

public class SetActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        setTitle("设置");
        setLeftIcon(R.mipmap.fanhui);
    }
}
