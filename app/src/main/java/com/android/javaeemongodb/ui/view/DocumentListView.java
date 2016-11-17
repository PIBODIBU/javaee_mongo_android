package com.android.javaeemongodb.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.data.model.MedicineModel;

public interface DocumentListView {
    Context getContext();

    AppCompatActivity getActivity();

    @Nullable
    RecyclerView getRecyclerView();

    void setRefreshing(boolean refreshing);

    void setupView();

    boolean isRefreshing();

    void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener);

    void showSnackBar(String message);

    void startModelInfoActivity(MedicineModel model);

    void startModelEditActivity(MedicineModel model);

    void setSelectionModelActivated(boolean activated);
}
