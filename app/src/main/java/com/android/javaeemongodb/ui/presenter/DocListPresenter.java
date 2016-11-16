package com.android.javaeemongodb.ui.presenter;

import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.ui.adapter.DocListAdapter;
import com.android.javaeemongodb.ui.view.DocListView;

public interface DocListPresenter {
    void start();

    void onDestroy();

    void setupRecyclerView(RecyclerView recyclerView);

    void setupAdapter(DocListAdapter adapter);

    void setupProcessor();

    DocListView getView();

    void refreshDataSet();
}
