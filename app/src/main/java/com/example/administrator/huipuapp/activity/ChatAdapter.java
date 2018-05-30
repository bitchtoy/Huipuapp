package com.example.administrator.huipuapp.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.huipuapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator.
 * @date 2018/5/8.
 * @funtion
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    public ChatAdapter(RecyclerView recyclerView) {
        mContext = recyclerView.getContext();
    }
    public void setDatas(List<String> strings){
        if (mList != null){
            mList.addAll(strings);
            notifyDataSetChanged();
        }

    }
    @Override
    public ChatHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_layout,viewGroup,false);
        ChatHolder holder = new ChatHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatHolder chatHolder, int i) {
       chatHolder.mImageView.setImageResource(R.drawable.zl);
       chatHolder.mTextView.setText(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ChatHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView;
        public ChatHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_activity_we_bottle_detail_img_one);
            mTextView = (TextView) itemView.findViewById(R.id.tv_activity_we_bottle_detail_msg_one);
        }
    }
}
