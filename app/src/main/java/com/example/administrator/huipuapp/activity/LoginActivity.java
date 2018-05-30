package com.example.administrator.huipuapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.huipuapp.R;
import com.example.administrator.huipuapp.bean.LoginBean;
import com.example.administrator.huipuapp.untils.MyTools;
import com.example.administrator.huipuapp.untils.MyUrl;
import com.example.administrator.huipuapp.untils.SharedPreferenceUtil;
import com.example.administrator.huipuapp.untils.UtilBox;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/22.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.phone_text)
    EditText phoneText;
    @BindView(R.id.password_text)
    EditText passwordText;
    @BindView(R.id.login_bnt)
    TextView loginBnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTitle("登录");
        boolean islogin = SharedPreferenceUtil.getBooleanData("islogin");
        if (islogin == true) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

//    @OnClick({R.id.login_bnt})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.login_bnt:
//                if (TextUtils.isEmpty(phoneText.getText().toString()) == true) {
//                    MyTools.showToast(getBaseContext(), "请输入用户名");
//                    return;
//                }
//                if (TextUtils.isEmpty(passwordText.getText().toString()) == true) {
//                    MyTools.showToast(getBaseContext(), "请输入密码");
//                    return;
//                }
//                Map<String, String> map = new HashMap<>();
//                map.put("accounts", "" + phoneText.getText().toString());
//                map.put("password", "" + passwordText.getText().toString());
//                OkHttpUtils.post().params(map).url(MyUrl.login).build().execute(new StringCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        MyTools.showToast(getBaseContext(), "网络错误");
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        LoginBean loginBean = new Gson().fromJson(response, LoginBean.class);
//                        if (loginBean.getSuccess().equals("0")) {
//                            MyTools.showToast(getBaseContext(), "" + loginBean.getSuccess());
//                        } else {
//                            MyTools.showToast(getBaseContext(), "登录成功");
//                            SharedPreferenceUtil.SaveData("islogin", true);
//                            SharedPreferenceUtil.SaveData("id",loginBean.getUseruuid());
//                            SharedPreferenceUtil.SaveData("name",loginBean.getUsername());
//                            MyTools.showToast(getBaseContext(), "登录成功");
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                });
//
//                break;
//        }
//    }
}
