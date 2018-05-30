package com.example.administrator.huipuapp.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.huipuapp.R;
import com.example.administrator.huipuapp.bean.LoginBean;
import com.example.administrator.huipuapp.untils.ExampleUtil;
import com.example.administrator.huipuapp.untils.MyTools;
import com.example.administrator.huipuapp.untils.MyUrl;
import com.example.administrator.huipuapp.untils.SharedPreferenceUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;


import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class IndexActivity extends BaseActivity {


    @BindView(R.id.iv_shape)
    ImageView iv_shape;
    @BindView(R.id.iv_shape_right)
    ImageView iv_shape_right;
    @BindView(R.id.iv_shape_centre)
    ImageView iv_shape_centre;
    @BindView(R.id.tv_activity_index_start)
    TextView tv_activity_index_start;
    @BindView(R.id.rl_activity_index_our_pingzi)
    RelativeLayout rl_activity_index_our_pingzi;
    @BindView(R.id.rl_one)
    RelativeLayout rl_one;
    //
    @BindView(R.id.activity_index)
    RelativeLayout activity_index;

    @BindView(R.id.ll_dialog)
    LinearLayout ll_dialog;

    @BindView(R.id.tv_dialog_ok)
    Button tv_dialog_ok;
    @BindView(R.id.tv_activity_index_count)
    TextView tv_activity_index_count;


    @BindView(R.id.ll_dialog_send_message)//群发布局
            LinearLayout ll_dialog_send_message;

    @BindView(R.id.et_msg)//群发内容
            EditText et_msg;
    @BindView(R.id.tv_send_msg)//群发按钮
            TextView tv_send_msg;

    @BindView(R.id.rl_two)
    RelativeLayout rl_two;
    @BindView(R.id.index_set)
    Button set;


    private AnimationDrawable animationDrawable;
    private AnimationDrawable animationDrawableRight;
    private AnimationDrawable animationDrawableCentre;

    private boolean flag = true;//表示开始   false 复位

    private String registrationId;


    public static boolean isForeground = false;


    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        settigone();
        ButterKnife.bind(this);
        //获取 AnimationDrawable
        animationDrawable = (AnimationDrawable) iv_shape.getDrawable();
        animationDrawableCentre = (AnimationDrawable) iv_shape_centre.getDrawable();
        animationDrawableRight = (AnimationDrawable) iv_shape_right.getDrawable();
        animationDrawable.start();
        animationDrawableCentre.start();
        animationDrawableRight.start();
        registerMessageReceiver();  // used for receive msg


    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String MESSAGE_REGISTRATION_ID = "com.example.jpushdemo.MESSAGE_REGISTRATION_ID";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        filter.addAction(MESSAGE_REGISTRATION_ID);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                } else if (MESSAGE_REGISTRATION_ID.equals(intent.getAction())) {
                    registrationId = JPushInterface.getRegistrationID(IndexActivity.this);
                    SharedPreferenceUtil.SaveData("registrationId", registrationId);
                    System.out.println("dafdasfdafdfa-------" + registrationId);
                    //存储rid
                }
            } catch (Exception e) {
            }
        }
    }


    @OnClick({R.id.tv_activity_index_start, R.id.rl_one, R.id.tv_dialog_ok, R.id.activity_index, R.id.rl_activity_index_our_pingzi, R.id.rl_two, R.id.tv_send_msg,R.id.index_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_activity_index_start:
                if (flag) {

                    startM();
                } else {
                    reStartM();
                }

                break;
            case R.id.rl_activity_index_our_pingzi:
                ll_dialog.setVisibility(View.GONE);
                ll_dialog_send_message.setVisibility(View.GONE);
                Intent intent = new Intent(IndexActivity.this, WeBottleListActivity.class);
                startActivity(intent);

                break;
            case R.id.rl_one://扔瓶子  提示今天的扔瓶子机会用完了

                ll_dialog_send_message.setVisibility(View.GONE);
                ll_dialog.setVisibility(View.VISIBLE);

                break;
            case R.id.rl_two://捡瓶子  群发消息
                ll_dialog.setVisibility(View.GONE);
                ll_dialog_send_message.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dialog_ok:
                ll_dialog.setVisibility(View.GONE);
                break;
            case R.id.tv_send_msg://群发按钮
                if (ExampleUtil.isEmpty(et_msg.getText().toString())) {
                    ExampleUtil.showToast("请输入群发消息", IndexActivity.this);

                } else {
                    ll_dialog_send_message.setVisibility(View.GONE);
                }

                break;
            case R.id.activity_index:
                ll_dialog.setVisibility(View.GONE);
                ll_dialog_send_message.setVisibility(View.GONE);
                break;
            case R.id.index_set:
                Intent intent1 = new Intent(IndexActivity.this,SettingActivity.class);
                startActivity(intent1);
//                new RxPermissions(this).
//                        request(Manifest.permission.CAMERA,
//                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                Manifest.permission.READ_EXTERNAL_STORAGE)
//                        .subscribe(new Observer<Boolean>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(Boolean aBoolean) {
//                            if (aBoolean){
//
//                            }
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });

                break;
            default:
                break;
        }
    }

//    private void showCaram() {
//        PhotoPickerIntent photo = new PhotoPickerIntent(IndexActivity.this);
//        photo.setPhotoCount(9);
//        photo.setShowCamera(false);
//        startActivityForResult(photo, 20);
//    }


    private void startM() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(MyUrl.START + "registration_id=" + SharedPreferenceUtil.getStringData("registrationId"));
                    System.out.println("-----------registrationId-startM--" + SharedPreferenceUtil.getStringData("registrationId"));
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer = new StringBuffer();
                        String readaLine = "";
                        while ((readaLine = bufferedReader.readLine()) != null) {
                            stringBuffer.append(readaLine);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        httpURLConnection.disconnect();
                        System.out.println("---------" + stringBuffer.toString());
                        Gson gson = new Gson();
                        LoginBean person = gson.fromJson(stringBuffer.toString(), LoginBean.class);//对于javabean直接给出class实例


                        if (person.getState().equals("0")) {
                            //耗时操作，完成之后发送消息给Handler，完成UI更新；
                            mHandler.sendEmptyMessage(0);
                            //需要数据传递，用下面方法；
                            Message msg = new Message();
                            msg.obj = "网络数据";//可以是基本类型，可以是对象，可以是List、map等；
                            mHandler.sendMessage(msg);


                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                }


            }
        }).start();


    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tv_activity_index_start.setText("复位");
                    flag = false;
                    break;
                case 1:
                    count = 0;
                    tv_activity_index_count.setVisibility(View.GONE);
                    tv_activity_index_count.setText(String.valueOf(count));
                    tv_activity_index_start.setText("漂流瓶");
                    flag = true;
                    break;
                default:
                    break;
            }
        }
    };


    //程序结束  点击复位
    private void reStartM() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(MyUrl.EXIT + "registration_id=" + SharedPreferenceUtil.getStringData("registrationId"));
                    System.out.println("-----------registrationId-startM--" + SharedPreferenceUtil.getStringData("registrationId"));
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer = new StringBuffer();
                        String readaLine = "";
                        while ((readaLine = bufferedReader.readLine()) != null) {
                            stringBuffer.append(readaLine);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        httpURLConnection.disconnect();
                        System.out.println("---------" + stringBuffer.toString());
                        Gson gson = new Gson();
                        LoginBean person = gson.fromJson(stringBuffer.toString(), LoginBean.class);//对于javabean直接给出class实例


                        if (person.getState().equals("0")) {
                            //耗时操作，完成之后发送消息给Handler，完成UI更新；
//                            mHandler.sendEmptyMessage(0);
                            //需要数据传递，用下面方法；
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = "网络数据";//可以是基本类型，可以是对象，可以是List、map等；
                            mHandler.sendMessage(msg);


                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                }


            }
        }).start();


    }

    private void setCostomMsg(String msg) {
        count += 1;
        tv_activity_index_count.setVisibility(View.VISIBLE);
        System.out.println("-------setCostomMsg------------" + msg);
        tv_activity_index_count.setText(String.valueOf(count));
    }

}
