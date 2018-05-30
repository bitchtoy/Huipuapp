package com.example.administrator.huipuapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.huipuapp.R;
import com.example.administrator.huipuapp.adpater.BottleAdapter;
import com.example.administrator.huipuapp.bean.BottleBean;
import com.example.administrator.huipuapp.untils.MyUrl;
import com.example.administrator.huipuapp.untils.SharedPreferenceUtil;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeBottleDetailActivity extends BaseActivity {




    @BindView(R.id.iv_activity_we_bottle_back)
    ImageView iv_activity_we_bottle_back;

    @BindView(R.id.tv_activity_we_bottle_title)
    TextView tv_activity_we_bottle_title;

    @BindView(R.id.tv_activity_we_bottle_detail_time)
    TextView tv_activity_we_bottle_detail_time;
    @BindView(R.id.iv_activity_we_bottle_detail_img)
    ImageView iv_activity_we_bottle_detail_img;

    @BindView(R.id.tv_activity_we_bottle_detail_msg)
    TextView tv_activity_we_bottle_detail_msg;
    @BindView(R.id.recycle_view)
    RecyclerView rcy;

    private BottleBean.Bottle bottle;
    private  List<BottleBean.Messages> messagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_bottle_detail);
        settigone();
        ButterKnife.bind(this);
        messagesList=(List<BottleBean.Messages>)getIntent().getSerializableExtra("msgLists");
        bottle=(BottleBean.Bottle) getIntent().getSerializableExtra("bottle");
        tv_activity_we_bottle_title.setText("来自"+bottle.getBottle_user_area().toString()+"的瓶子");
        tv_activity_we_bottle_detail_time.setText(messagesList.get(0).getMessage_time().toString());
        Glide.with(WeBottleDetailActivity.this).load(MyUrl.BASEURL +
                bottle.getBottle_user_name().toString().toString())
                .into(iv_activity_we_bottle_detail_img);
        tv_activity_we_bottle_detail_msg.setText(messagesList.get(0).getMessage_content().toString());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcy.setLayoutManager(manager);
        ChatAdapter adapter = new ChatAdapter(rcy);
        rcy.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        list.add("dskmkd");
        list.add("asdjiosd");
        adapter.setDatas(list);

    }

    @OnClick({R.id.iv_activity_we_bottle_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_activity_we_bottle_back:
                finish();
                break;

        }
    }

}
