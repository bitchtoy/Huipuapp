package com.example.administrator.huipuapp.adpater;

import android.content.Context;

import com.example.administrator.huipuapp.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */

public class NotReadAdpater extends BaseRecycleAdapter<String> {
    public NotReadAdpater(List<String> datas, Context context) {
        super(datas, context);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.adpater_not_read;
    }
}
