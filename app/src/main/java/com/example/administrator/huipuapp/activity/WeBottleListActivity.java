package com.example.administrator.huipuapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.huipuapp.R;
import com.example.administrator.huipuapp.adpater.BottleAdapter;
import com.example.administrator.huipuapp.bean.BottleBean;
import com.example.administrator.huipuapp.bean.LoginBean;
import com.example.administrator.huipuapp.untils.ExampleUtil;
import com.example.administrator.huipuapp.untils.MyTools;
import com.example.administrator.huipuapp.untils.MyUrl;
import com.example.administrator.huipuapp.untils.SharedPreferenceUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class WeBottleListActivity extends BaseActivity {




    @BindView(R.id.iv_activity_we_bottle_back)
    ImageView iv_activity_we_bottle_back;

    @BindView(R.id.tv_activity_we_bottle_back)
    TextView tv_activity_we_bottle_back;

    @BindView(R.id.tv_emtry_message)
    TextView tv_emtry_message;
    @BindView(R.id.lv_activity_we_bottle_list)
    ListView lv_activity_we_bottle_list;

    private  int nowPage=1;

    private  List<BottleBean.Bottle> bottleList;
    private  List<BottleBean.Messages> messagesList;

    private  List<List<BottleBean.Messages>> msgLists;

    private BottleAdapter bottleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_bottle_list);
        settigone();
        ButterKnife.bind(this);
        bottleList=new ArrayList<BottleBean.Bottle>();
        messagesList=new ArrayList<BottleBean.Messages>() ;
        msgLists=new ArrayList<List<BottleBean.Messages>>();
        noticeList();
        lv_activity_we_bottle_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(WeBottleListActivity.this,WeBottleDetailActivity.class);
                intent.putExtra("msgLists", (Serializable) msgLists.get(i));
                intent.putExtra("bottle", (Serializable)bottleList.get(i));
                startActivity(intent);

            }
        });

    }


    @OnClick({R.id.iv_activity_we_bottle_back,R.id.tv_activity_we_bottle_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_activity_we_bottle_back:
            case R.id.tv_activity_we_bottle_back:
                finish();
                break;

        }
    }


    private void noticeList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //http://210.209.72.186/messageInter/getBottle.do?registration_id=1111&nowPage=1
                    URL url=new URL(MyUrl.NOTICE+"registration_id="+ SharedPreferenceUtil.getStringData("registrationId")+"&nowPage="+nowPage);
                    System.out.println("-----------registrationId-startM--"+SharedPreferenceUtil.getStringData("registrationId"));
                    HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode()==200){
                        InputStream inputStream=httpURLConnection.getInputStream();
                        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer=new StringBuffer();
                        String readaLine="";
                        while ((readaLine=bufferedReader.readLine())!=null){
                            stringBuffer.append(readaLine);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        httpURLConnection.disconnect();
                        System.out.println("---------"+stringBuffer.toString());
                        Gson gson = new Gson();
                        BottleBean bottleBean = gson.fromJson(stringBuffer.toString(), BottleBean.class);//对于javabean直接给出class实例
                        if (bottleBean.getState()==0){

                            List<BottleBean.CommunicationList> communicationLists=bottleBean.getCommunicationList();
                            for (int i=0;i<communicationLists.size();i++){

                                BottleBean.Bottle bottle=communicationLists.get(i).getBottle();
                                messagesList=communicationLists.get(i).getMessages();
                                bottleList.add(bottle);
                                msgLists.add(messagesList);

                            }

                            //需要数据传递，用下面方法；
                            Message msg = new Message();
                            msg.obj = "网络数据";//可以是基本类型，可以是对象，可以是List、map等；
                            msg.what=1;
                            mHandler.sendMessage(msg);


                        }
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();

                }catch (IOException e){
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
                case 1:
                    if (bottleList.size()>0){
                        lv_activity_we_bottle_list.setVisibility(View.VISIBLE);
                        tv_emtry_message.setVisibility(View.GONE);
                        bottleAdapter=new BottleAdapter(WeBottleListActivity.this,bottleList,msgLists);
                        lv_activity_we_bottle_list.setAdapter(bottleAdapter);

                    }else{
                        lv_activity_we_bottle_list.setVisibility(View.GONE);
                        tv_emtry_message.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    break;
            }
        }
    };





}
