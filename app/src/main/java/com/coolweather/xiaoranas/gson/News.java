package com.coolweather.xiaoranas.gson;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class News {
    public String reason;//成功的返回
    public Result result;

    public class Result {
        public String stat;
        public List<Data> data;
    }
    public class Data {
        public String uniquekey;
        public String title;
        public String date;
        public String category;
        public String author_name;
        public String url;
        public String thumbnail_pic_s;
        public String thumbnail_pic_s02;
        public String thumbnail_pic_s03;
    }
}
