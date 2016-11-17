package com.android.javaeemongodb.ui.presenter;

import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.adapter.DocumentListAdapter;
import com.android.javaeemongodb.ui.view.DocumentListView;

public interface DocListPresenter {
    void start();

    void onDestroy();

    void setupRecyclerView(RecyclerView recyclerView);

    void setupAdapter(DocumentListAdapter adapter);

    void setupProcessor();

    DocumentListView getView();

    DocumentListAdapter getAdapter();

    void refreshDataSet();

    void deleteModel(final int position, final MedicineModel model);
}
