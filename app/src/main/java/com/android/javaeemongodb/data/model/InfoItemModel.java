package com.android.javaeemongodb.data.model;

import android.support.annotation.DrawableRes;

public class InfoItemModel {
    private int imageRes;
    private String title;
    private String description;

    public InfoItemModel(@DrawableRes int imageRes, String title, String description) {
        this.imageRes = imageRes;
        this.title = title;
        this.description = description;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
