package com.coolweather.xiaoranas.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/2/22.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weather;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
