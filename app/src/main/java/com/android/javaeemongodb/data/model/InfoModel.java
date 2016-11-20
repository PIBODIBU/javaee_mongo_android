package com.android.javaeemongodb.data.model;

public class InfoModel {
    private String title;
    private String value;

    public InfoModel(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}
