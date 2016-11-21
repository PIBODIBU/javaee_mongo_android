package com.android.javaeemongodb.ui.presenter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.adapter.DocumentListAdapter;
import com.android.javaeemongodb.ui.view.DocumentListView;

import java.util.ArrayList;

public interface DocListPresenter {
    void start();

    void onDestroy();

    void setupRecyclerView(RecyclerView recyclerView);

    void setupAdapter(DocumentListAdapter adapter);

    void setupProcessor();

    DocumentListView getView();

    DocumentListAdapter getAdapter();

    ArrayList<MedicineModel> getDataSet();

    void refreshDataSet();

    void refreshDataSet(Boolean withIndication);

    void deleteModel(final int position, final MedicineModel model);

    void deleteManyModels();

    void setSelectionModeActivated(Boolean selectionModeActivated);

    void setLayoutManager(RecyclerView.LayoutManager layoutManager);

    LinearLayoutManager getLinearLayoutManager();

    GridLayoutManager getGridLayoutManager();
}
