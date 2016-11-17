package com.android.javaeemongodb.data.model;

public abstract class AbstractModel {
    private Boolean selected = false;

    public AbstractModel() {
    }

    public AbstractModel(Boolean selected) {
        this.selected = selected;
    }

    public Boolean isSelected() {
        return selected == null ? false : selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
