package com.coolweather.xiaoranas.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/27.
 */

public class ToastUtils {
    public static void ShowToast(Context context, Toast toast, String text){
        if (toast == null){
            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }else{
            toast.setText(text);
        }
        toast.show();
    }
}
