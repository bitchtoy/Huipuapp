package com.example.administrator.huipuapp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.example.administrator.huipuapp.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.utils.PhotoPickerIntent;


/**
 * @author Administrator.
 * @date 2018/5/8.
 * @funtion
 */

public class SettingActivity extends Activity {
    private ImageView mImageView;
    private LinearLayout mLinearLayout;
    private ImageView userHeard;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        mImageView = (ImageView) findViewById(R.id.setting_back);
        mLinearLayout = (LinearLayout) findViewById(R.id.open_photo_pick);
        userHeard = (ImageView) findViewById(R.id.user_heard);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RxPermissions(SettingActivity.this)
                        .request(Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                                ).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                     showCaram();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
    }
    private void showCaram() {

        PhotoPickerIntent photo = new PhotoPickerIntent(SettingActivity.this);
        photo.setShowCamera(false);
        photo.setPhotoCount(1);
        startActivityForResult(photo, 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20&& resultCode == RESULT_OK){
            ArrayList<String> photos =
                    data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
            Glide.with(this).load(photos.get(0)).into(userHeard);
        }
    }
}
