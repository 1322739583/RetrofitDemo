package com.example.retrofitdemo.mvp.model;

import java.util.Arrays;

public class DataModel {
    private String content;
    private String imgUrl[];

    public DataModel(String imgUrl[], String content) {
        this.content = content;
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String[] imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "DataModel2{" +
                "content='" + content + '\'' +
                ", imgUrl=" + Arrays.toString(imgUrl) +
                '}';
    }
}
