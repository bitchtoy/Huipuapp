package com.example.administrator.huipuapp.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.huipuapp.R;

/**
 * Created by Administrator on 2017/7/24.
 */

public class SubmitActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("流程提交");
        setLefttext("取消");
        setRightText("确认");
        setContentView(R.layout.activity_submit);
    }
}
