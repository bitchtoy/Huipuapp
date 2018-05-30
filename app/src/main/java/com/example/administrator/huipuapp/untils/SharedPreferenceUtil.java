package com.example.administrator.huipuapp.untils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Title: ${file_name}
 * @Description: ${todo}<请描述此文件是做什么的>
 * @author: 孙浩
 * @data: 2016/9/28 19:11
 * @version: V1.0
 */
public class SharedPreferenceUtil {
    public static SharedPreferences preference;
    public static void init(Context context , String name){
        preference = context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }
    public static void SaveData(String name,Object data){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(name,String.valueOf(data));
        editor.commit();
    }
    public static boolean getBooleanData(String name){
       return Boolean.valueOf(preference.getString(name,"false"));
    }
    public static String getStringData(String name){
        return preference.getString(name,"1111");
    }
    public static int getIntData(String name){
        return Integer.valueOf(preference.getString(name,"0"));
    }
    public static double getDoubleData(String name){
        return Double.valueOf(preference.getString(name,"0"));
    }
    public static long getLongData(String name){
        return Long.valueOf(preference.getString(name,"0"));
    }
}
