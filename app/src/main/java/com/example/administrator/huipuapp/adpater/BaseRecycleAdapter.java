package com.example.administrator.huipuapp.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：Bruce Lee
 * 时间 2017/3/16 09:27
 * 类说明{RecyclerView的基类  可以add  刷新数据  }
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseRecycleAdapter.BaseViewHolder> {


    protected List<T> datas;
    protected Context context;

    public BaseRecycleAdapter(List<T> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(),parent,false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseRecycleAdapter.BaseViewHolder holder, final int position) {

        bindData(holder,position);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(null, v, holder.getPosition(), holder.getItemId());
                }
            });
        }
    }
    private AdapterView.OnItemClickListener onItemClickListener;

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 刷新数据
     * @param datas
     */
    public void refresh(List<T> datas){
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     * @param datas
     */
    public void addData(List<T> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     *  绑定数据
     * @param holder  具体的viewHolder
     * @param position  对应的索引
     */
    protected abstract void bindData(BaseViewHolder holder, int position);



    @Override
    public int getItemCount() {

        return datas==null?0:datas.size();
    }




    /**
     * 封装ViewHolder ,子类可以直接使用
     */
    public class BaseViewHolder extends  RecyclerView.ViewHolder{


        private Map<Integer, View> mViewMap;

        public BaseViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            mViewMap = new HashMap<>();
        }

        /**
         * 获取设置的view
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = mViewMap.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewMap.put(id, view);
            }
            return view;
        }
    }

    /**
     * 获取子item
     * @return
     */
    public abstract int getLayoutId();
    /**
     * 设置文本属性
     * @param view
     * @param text
     */
    public void setItemText(View view, String text){
        if(view instanceof TextView){
            ((TextView) view).setText(text);
        }
    }
}