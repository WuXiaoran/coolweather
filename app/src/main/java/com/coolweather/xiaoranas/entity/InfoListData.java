package com.coolweather.xiaoranas.entity;

/**
 * Created by Administrator on 2017/2/27.
 */

public class InfoListData {
    private String title;
    private String top_image;
    private String source;
    private String reply_count;
    private String edit_time;

    public InfoListData(String title, String top_image, String source, String reply_count, String edit_time) {
        this.title = title;
        this.top_image = top_image;
        this.source = source;
        this.reply_count = reply_count;
        this.edit_time = edit_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTop_image() {
        return top_image;
    }

    public void setTop_image(String top_image) {
        this.top_image = top_image;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getReply_count() {
        return reply_count;
    }

    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }
}
