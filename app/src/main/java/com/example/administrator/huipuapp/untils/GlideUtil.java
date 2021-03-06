package com.example.administrator.huipuapp.untils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @Title: ${file_name}
 * @Description: ${todo}<Glide封装实体类>
 * @author: 孙浩
 * @data: 2016/10/20 13:42
 * @version: V1.0
 */
public class GlideUtil {

    /**
     * 设置普通图片
     * @param context
     * @param Url
     * @param iv
     */
   public static void ShowImage(Context context, String Url, ImageView iv){
        Glide.with(context).load(Url).into(iv);
    }

    /**
     * 设置圆角图片
     * @param context
     * @param url
     * @param iv
     * @param rudius
     */
    public static void ShowRoundCornerImg(Context context,String url, ImageView iv, int rudius){
        Glide.with(context).load(url).transform(new GlideRoundTransform(context,rudius)).into(iv);
    }

    /**
     * 设置圆形图片
     * @param context
     * @param url
     * @param iv
     */
    public static void ShowCircleImg(Context context,String url, ImageView iv){
//        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).error(R.drawable.touxiang).into(iv);
        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).into(iv);
    }
}
