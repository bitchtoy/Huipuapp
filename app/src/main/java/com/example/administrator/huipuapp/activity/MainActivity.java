package com.example.administrator.huipuapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.huipuapp.R;
import com.example.administrator.huipuapp.untils.MyTools;
import com.example.administrator.huipuapp.untils.SharedPreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/22.
 */

public class MainActivity extends BaseActivity implements BaseActivity.OnRightIconClick {
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.daiban)
    LinearLayout daiban;
    @BindView(R.id.yiban)
    LinearLayout yiban;
    @BindView(R.id.daiyue)
    LinearLayout daiyue;
    @BindView(R.id.yiyue)
    LinearLayout yiyue;
    @BindView(R.id.neiwang)
    LinearLayout neiwang;
    @BindView(R.id.tongxunl)
    LinearLayout tongxunl;
    @BindView(R.id.zhishi)
    LinearLayout zhishi;
    @BindView(R.id.fujian)
    LinearLayout fujian;
    @BindView(R.id.xiaoxi)
    LinearLayout xiaoxi;
    @BindView(R.id.shezhi)
    LinearLayout shezhi;
    @BindView(R.id.tuichu)
    LinearLayout tuichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("耀普软件");
        setRightIcon(R.mipmap.renovate);
        setOnRightIconClick(this);
        MyTools.showToast(getBaseContext(), "" + SharedPreferenceUtil.getStringData("id"));
    }


    @Override
    public void rightIconClick(View view) {
        MyTools.showToast(getBaseContext(), "设置");
    }

    @OnClick({R.id.daiban, R.id.yiban, R.id.daiyue, R.id.yiyue, R.id.neiwang, R.id.tongxunl, R.id.zhishi, R.id.fujian, R.id.xiaoxi, R.id.shezhi, R.id.tuichu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.daiban:
                startActivity(new Intent(getBaseContext(), NotBanActivity.class));
                break;
            case R.id.yiban:
                startActivity(new Intent(getBaseContext(), YibanBanActivity.class));
                break;
            case R.id.daiyue:
                startActivity(new Intent(getBaseContext(), YiyueBanActivity.class));
                break;
            case R.id.yiyue:
                startActivity(new Intent(getBaseContext(), YiyBanActivity.class));
                break;
            case R.id.neiwang:
                break;
            case R.id.tongxunl:
                startActivity(new Intent(getBaseContext(), TongxunlvActivity.class));
                break;
            case R.id.zhishi:
                break;
            case R.id.fujian:
                break;
            case R.id.xiaoxi:
                break;
            case R.id.shezhi:
                startActivity(new Intent(getBaseContext(), SetActivity.class));
                break;
            case R.id.tuichu:
                finish();
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
                SharedPreferenceUtil.SaveData("islogin", false);
                break;
        }
    }
}
