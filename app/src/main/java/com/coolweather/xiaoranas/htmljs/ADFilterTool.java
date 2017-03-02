package com.coolweather.xiaoranas.htmljs;

import android.content.Context;
import android.content.res.Resources;

import com.coolweather.xiaoranas.R;

/**
 * Created by Administrator on 2017/3/1.
 */

public class ADFilterTool {
    public  static boolean  hasAd(Context context, String url){
        Resources res= context.getResources();
        String[]adUrls =res.getStringArray(R.array.adBlockUrl);
        for(String  adUrl :adUrls){
            if(url.contains(adUrl)){
                return  true;
            }
        }
        return  false;
    }
}
