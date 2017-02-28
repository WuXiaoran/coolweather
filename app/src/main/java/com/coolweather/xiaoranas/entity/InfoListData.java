package com.coolweather.xiaoranas.entity;

/**
 * Created by Administrator on 2017/2/27.
 */

public class InfoListData {
    private String title;
    private String top_image;
    private String source;
    private String edit_time;
    private String image_1;
    private String image_2;

    public InfoListData(String title, String top_image, String source, String edit_time) {
        this.title = title;
        this.top_image = top_image;
        this.source = source;
        this.edit_time = edit_time;
    }

    public InfoListData(String title, String top_image, String source, String edit_time,String image_1,String image_2) {
        this.title = title;
        this.image_1 = image_1;
        this.edit_time = edit_time;
        this.image_2 = image_2;
        this.top_image = top_image;
        this.source = source;
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

    public String getImage_1() {
        return image_1;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }
}
