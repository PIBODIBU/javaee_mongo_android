package com.android.javaeemongodb.ui.presenter;

import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.ui.adapter.InfoItemListAdapter;
import com.android.javaeemongodb.ui.view.InfoView;

public interface InfoPresenter {
    void start();

    void onDestroy();

    void setupRecyclerView(RecyclerView recyclerView);

    void setupAdapter(InfoItemListAdapter adapter);

    void setupProcessor();

    InfoItemListAdapter getAdapter();

    InfoView getView();
}
