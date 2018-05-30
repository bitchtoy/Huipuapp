package com.example.administrator.huipuapp.untils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016-11-26.
 */
public class ShowPopUtils {

    private static PopupWindow popupWindow;
    private static Context context;
    private static LayoutInflater inflater;

    private ShowPopUtils(Context context, LayoutInflater inflater) {
        this.context = context;
        this.inflater = inflater;
    }

    /**
     *
     *显示pop居中
     *
     * */
    public static PopupWindow showPopCenter(View v,View dialogPop,View activityRegisterViewbg) {
        activityRegisterViewbg.setVisibility(View.VISIBLE);
        //dialogPop = inflater.inflate(id,null);
        popupWindow = new PopupWindow(dialogPop, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置pop点击外面消失
        //popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        return popupWindow;

    }
    /**
     *
     *显示pop之上
     *
     * */
    public static PopupWindow showPopUp(View v,View dialogPop,View activityRegisterViewbg) {
        activityRegisterViewbg.setVisibility(View.VISIBLE);
        //dialogPop = inflater.inflate(id, null);
        popupWindow = new PopupWindow(dialogPop, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置pop点击外面消失
        //popupWindow.setBackgroundDrawable(new BitmapDrawable());
        int[] location = new int[2];
        v.getLocationOnScreen(location);

        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1]-popupWindow.getHeight());
        return popupWindow;

    }


    /**
     *
     *显示pop之下
     *
     * */
    public static void showPopDown(View v,View dialogPop,View activityRegisterViewbg,PopupWindow popupWindow) {
        activityRegisterViewbg.setVisibility(View.VISIBLE);
        //dialogPop = inflater.inflate(id, null);
        //popupWindow = new PopupWindow(dialogPop, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置pop点击外面消失
        //popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(v);
    }

    /**
     *
     *显示pop底部
     *
     * */
    public static PopupWindow showPopBottom(View v,View dialogPop,View activityRegisterViewbg) {
        activityRegisterViewbg.setVisibility(View.VISIBLE);
        //dialogPop = inflater.inflate(id,null);
        popupWindow = new PopupWindow(dialogPop, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置pop点击外面消失
        //popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(v,Gravity.BOTTOM |Gravity.CENTER_HORIZONTAL, 0, 10);
        dialogPop.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

               /* int height = dialogPop.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }*/
                return true;
            }
        });
        return popupWindow;

    }
}
