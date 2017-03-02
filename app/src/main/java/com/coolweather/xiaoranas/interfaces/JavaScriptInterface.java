package com.coolweather.xiaoranas.interfaces;

import android.content.Context;
import android.content.Intent;

import com.coolweather.xiaoranas.activity.ImageListActivity;

/**
 * Created by Administrator on 2017/3/1.
 */

public class JavaScriptInterface {
    //js通信接口
    private Context context;

    public JavaScriptInterface(Context context) {
        this.context = context;

    }
    public void openImage(String img){
        Intent intent = new Intent();
        intent.putExtra("image_url",img);
        intent.setClass(context, ImageListActivity.class);
        context.startActivity(intent);
    }
}
