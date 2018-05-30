package com.example.administrator.huipuapp.view;

import android.app.Application;
import android.content.Context;

import com.example.administrator.huipuapp.untils.SharedPreferenceUtil;
import com.squareup.okhttp.OkHttpClient;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/7/21.
 */

public class App extends Application {
    private Context mContext;
    private  String registrationId;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getBaseContext();
        SharedPreferenceUtil.init(getBaseContext(),"");
        AutoLayoutConifg.getInstance().useDeviceSize();
        OkHttpClient client =
                OkHttpUtils.getInstance().getOkHttpClient();
        client.setConnectTimeout(100000, TimeUnit.MILLISECONDS);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());

    }
}
