package com.example.administrator.huipuapp.adpater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.huipuapp.R;
import com.example.administrator.huipuapp.bean.BottleBean;
import com.example.administrator.huipuapp.untils.MyUrl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cn.jiguang.net.HttpConstants;

/**
 * @author Administrator.
 * @date 2018/1/18.
 * @funtion
 */

public class BottleAdapter extends BaseAdapter {

    private Context mContext = null;
    private LayoutInflater mInflater = null;
    private List<BottleBean.Bottle> mList = null;
    private List<List<BottleBean.Messages>> msgLists;


    public BottleAdapter(Context context, List<BottleBean.Bottle> list, List<List<BottleBean.Messages>> msgLists) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
        this.msgLists = msgLists;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FlipperViewHolder holder = null;
        if (convertView == null) {
            holder = new FlipperViewHolder();
            convertView = mInflater.inflate(R.layout.activity_bottle_item, parent, false);
            holder.iv_activity_bottle_item_img = (ImageView) convertView.findViewById(R.id.iv_activity_bottle_item_img);
            holder.tv_activity_bottle_item_area = (TextView) convertView.findViewById(R.id.tv_activity_bottle_item_area);
            holder.tv_activity_bottle_item_final_msg = (TextView) convertView.findViewById(R.id.tv_activity_bottle_item_final_msg);
            holder.tv_activity_bottle_item_time = (TextView) convertView.findViewById(R.id.tv_activity_bottle_item_time);
            convertView.setTag(holder);
            //对于listview，注意添加这一行，即可在item上使用高度
//            AutoUtils.autoSize(convertView);
        } else {
            holder = (FlipperViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(MyUrl.BASEURL +
                mList.get(position).getBottle_user_name().toString())
                .into(holder.iv_activity_bottle_item_img);
        holder.tv_activity_bottle_item_area.setText(mList.get(position).getBottle_user_area().toString());
        holder.tv_activity_bottle_item_final_msg.setText(msgLists.get(position).get(0).getMessage_content().toString());
        holder.tv_activity_bottle_item_time.setText(mList.get(position).getBottle_time().toString());

        return convertView;
    }

    private class FlipperViewHolder {

        ImageView iv_activity_bottle_item_img;
        TextView tv_activity_bottle_item_area;
        TextView tv_activity_bottle_item_final_msg;
        TextView tv_activity_bottle_item_time;
    }


}
