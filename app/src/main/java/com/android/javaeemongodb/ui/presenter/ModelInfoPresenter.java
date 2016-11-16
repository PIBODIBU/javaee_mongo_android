package com.android.javaeemongodb.ui.presenter;

import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.ui.adapter.ModelInfoItemListAdapter;
import com.android.javaeemongodb.ui.view.ModelInfoView;

public interface ModelInfoPresenter {
    void start();

    void onDestroy();

    void setupRecyclerView(RecyclerView recyclerView);

    void setupAdapter(ModelInfoItemListAdapter adapter);

    void setupProcessor();

    ModelInfoItemListAdapter getAdapter();

    ModelInfoView getView();
}
