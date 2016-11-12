package com.android.javaeemongodb.ui.presenter;

import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.ui.adapter.DocListAdapter;

public interface DocListPresenter {

    void start();

    void onDestroy();

    void setupRecyclerView(RecyclerView recyclerView);

    void setupAdapter(DocListAdapter adapter);

    void setupProcessor();
}
