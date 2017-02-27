package com.coolweather.xiaoranas.gson;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class Get_NEWS {
    public int status;
    public String error;
    public int count;
    public List<Data> data;


    public class Data {
        public String news_id;
        public String title;
        public String top_image;
        public String text_image0;
        public String text_image1;
        public String source;
        public String content;
        public String digest;
        public String reply_count;
        public String edit_time;
    }
}
