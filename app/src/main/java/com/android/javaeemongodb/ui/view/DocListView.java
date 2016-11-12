package com.android.javaeemongodb.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

public interface DocListView {

    Context getContext();

    @Nullable
    RecyclerView getRecyclerView();

    void setRefreshing(boolean refreshing);

    void setupView();

    boolean isRefreshing();

    void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener);
}
