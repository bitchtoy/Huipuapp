package com.example.administrator.huipuapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.administrator.huipuapp.R;
import com.example.administrator.huipuapp.adpater.NotReadAdpater;
import com.example.administrator.huipuapp.untils.RecycleViewDivider;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待办
 * Created by Administrator on 2017/7/22.
 */

public class NotBanActivity extends BaseActivity {
    @BindView(R.id.shop_recycle)
    RecyclerView shopRecycle;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ban);
        ButterKnife.bind(this);
        setTitle("待办流程");
        setLeftIcon(R.mipmap.fanhui);
        inviteRefresh();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(i + "");
        }
        NotReadAdpater notReadAdpater = new NotReadAdpater(list, getBaseContext());
        RecycleViewDivider recycleViewDivider = new RecycleViewDivider(getBaseContext(), LinearLayout.HORIZONTAL, 2, R.color.viewback);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shopRecycle.addItemDecoration(recycleViewDivider);
        shopRecycle.setLayoutManager(layoutManager);
        shopRecycle.setAdapter(notReadAdpater);
        notReadAdpater.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getBaseContext(), MesgeActivity.class));
            }
        });
    }

    /**
     * 初始化上拉刷新  下拉加载
     */
    private void inviteRefresh() {
        refreshLayout.setHeaderView(new SinaRefreshView(getBaseContext()));
        refreshLayout.setBottomView(new LoadingView(getBaseContext()));
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);


            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);

            }
        });
    }
}
