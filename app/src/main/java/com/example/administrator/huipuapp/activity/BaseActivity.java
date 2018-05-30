package com.example.administrator.huipuapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.huipuapp.R;
import com.example.administrator.huipuapp.untils.StatusBarCompat;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

/**
 * 作者：li
 * 时间 2017/3/11 08:58
 * 类说明{}
 * QQ：243280864
 */
public abstract class BaseActivity extends AutoLayoutActivity implements BaseView {
    /**
     * Activity集合
     */
    private ArrayList<Activity> oList;
    private BaseActivity oContext;
    private Toolbar toolbar;
    private LinearLayout linearLayout;
    private ImageView ivRightIcon;
    private TextView tvTitle;
    private OnRightIconClick rightIconClick;
    private OnLeftIconClick leftIconClick;
    private OnRightTextClick rightTextClick;
    private ONCenterTextClick onCenterTextClick;
    private TextView tvCountDown;
    private Toolbar supportActionBar;
    private TextView lefttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        // 经测试在代码里直接声明透明状态栏更有效（设置状态栏透明）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        StatusBarCompat.setStatusBarColor(this,R.color.statusbar);
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.statusbar));
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        oContext = this;
        addActivity();
    }

    /**
     * 绑定view(用LinearLayout去add自己子activity的布局)
     *
     * @param view
     */
    public void setContentView(View view) {
        linearLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (linearLayout == null) return;
        linearLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    /**
     * 初始化标题
     * 由于toolbar的标题无法居中，而且有限制，所以设置标题为空字符串，自己配置标题样式
     */
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    /**
     * 设置自己的标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tvCountDown = (TextView) findViewById(R.id.tv_count_down);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lefttext = (TextView) findViewById(R.id.left_text);
        lefttext.setVisibility(View.GONE);
        tvTitle.setVisibility(View.VISIBLE);
//        toolbar.setNavigationIcon(null);
        tvTitle.setText(title);
        tvCountDown.setVisibility(View.GONE);
    }

    public void settigone() {
        toolbar.setVisibility(View.GONE);
    }

    public void settivist() {
        toolbar.setVisibility(View.VISIBLE);
    }

    /**
     * 设置头部颜色
     *
     * @param color
     */
    public void setHeadColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    /**
     * 左侧图标
     *
     * @param resId
     */
    public void setLeftIcon(int resId) {
        toolbar.setNavigationIcon(resId);
        lefttext = (TextView) findViewById(R.id.left_text);
        if (lefttext != null) {
            lefttext.setVisibility(View.GONE);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                if (leftIconClick != null) {
                    leftIconClick.leftIconClick(view);
                }
            }
        });
    }

    /**
     * 左侧文字
     */
    public void setLefttext(String s) {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        toolbar.setNavigationIcon(null);
        tvTitle.setVisibility(View.GONE);
        lefttext = (TextView) findViewById(R.id.left_text);
        lefttext.setVisibility(View.VISIBLE);
        lefttext.setText(s);

    }

    public String gettext() {
        tvCountDown = (TextView) findViewById(R.id.tv_count_down);
        return tvCountDown.getText().toString();
    }

    /**
     * 中间文字
     */

    public void setcontertext() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        if (tvTitle != null) {
            tvTitle.setVisibility(View.GONE);
        }

    }

    /**
     * 右侧图标
     *
     * @param resId
     */
    public void setRightIcon(int resId) {
        ivRightIcon = (ImageView) findViewById(R.id.iv_right_icon);
        tvCountDown = (TextView) findViewById(R.id.tv_count_down);
        ivRightIcon.setVisibility(View.VISIBLE);
        if (tvCountDown != null) {
            tvCountDown.setVisibility(View.GONE);
        }
        ivRightIcon.setImageResource(resId);
        ivRightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightIconClick != null) {
                    rightIconClick.rightIconClick(view);
                }
            }
        });
    }

    /**
     * 右侧文字
     *
     * @param text
     */
    public void setRightText(String text) {
        tvCountDown = (TextView) findViewById(R.id.tv_count_down);
        ivRightIcon = (ImageView) findViewById(R.id.iv_right_icon);
        tvCountDown.setVisibility(View.VISIBLE);
        if (ivRightIcon != null) {
            ivRightIcon.setVisibility(View.GONE);
        }
        tvCountDown.setText(text);
        tvCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightTextClick != null) {
                    rightTextClick.rightTextClick(view);
                }
            }
        });
    }

    /**
     * 右侧文字显隐状态
     *
     * @param gone
     */
    public void setRightTextGone(boolean gone) {
        if (gone) {
            tvCountDown.setVisibility(View.GONE);
        } else {
            tvCountDown.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置没有标题栏（全屏，状态栏透明）
     */
    public void setToolbarGone() {
        toolbar.setVisibility(View.GONE);
    }

    /**
     * 右侧图标点击
     *
     * @param rightIconClick
     */
    public void setOnRightIconClick(OnRightIconClick rightIconClick) {
        this.rightIconClick = rightIconClick;
    }

    /**
     * 中间图标点击
     */
    public void setOnCenterTextClick(ONCenterTextClick onCenterTextClick) {
        this.onCenterTextClick = onCenterTextClick;
    }

    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }
    //设置状态栏


    public interface OnRightIconClick {
        void rightIconClick(View view);
    }

    /**
     * 右侧文字点击
     *
     * @param rightTextClick
     */
    public void setOnRightTextClick(OnRightTextClick rightTextClick) {
        this.rightTextClick = rightTextClick;
    }

    public interface OnRightTextClick {
        void rightTextClick(View view);
    }

    public interface OnLeftIconClick {
        void leftIconClick(View view);
    }

    public interface ONCenterTextClick {
        void centerTextClick(View view);
    }

    /**
     * 左侧图标点击
     *
     * @param leftIconClick
     */
    public void setOnLeftIconClick(OnLeftIconClick leftIconClick) {
        this.leftIconClick = leftIconClick;
    }


    public BaseActivity() {
        //构造方法创建装Activity的list
        if (oList == null) {
            oList = new ArrayList<Activity>();
        }
    }

    /**
     * 界面跳转
     *
     * @param cls
     */
    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }


    /**
     * 界面跳转并kill掉本页
     *
     * @param cls
     */
    public void goToActivityFinish(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 添加Activity
     */
    public void addActivity_(Activity activity) {
        // 判断当前集合中不存在该Activity
        if (!oList.contains(activity)) {
            oList.add(activity);//把当前Activity添加到集合中
        }
    }

    public void addActivity() {
        addActivity_(oContext);

    }

    public void removeActivity() {
        removeActivity_(oContext);
    }

    public void removeALLActivity() {
        removeALLActivity_();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        OkHttpUtils.getInstance().cancelTag(this);//取消以Activity.this作为tag的请求
        removeActivity();
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity_(Activity activity) {
        //判断当前集合中存在该Activity
        if (oList.contains(activity)) {
            oList.remove(activity);//从集合中移除
        }
    }

    /**
     * 销毁所有的Activity
     */
    public void removeALLActivity_() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : oList) {
            activity.finish();
        }
    }

    //网络 不可用
    public boolean isNetAviliable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            return false;
            //改变背景或者 处理网络的全局变量
        } else {
            //改变背景或者 处理网络的全局变量
            return true;
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
