package com.android.javaeemongodb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.helper.Colors;
import com.android.javaeemongodb.helper.IntentKeys;
import com.android.javaeemongodb.ui.activity.base.BaseNavDrawerActivity;
import com.android.javaeemongodb.ui.presenter.InfoPresenter;
import com.android.javaeemongodb.ui.presenter.implementation.InfoPresenterImpl;
import com.android.javaeemongodb.ui.view.InfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends BaseNavDrawerActivity implements InfoView {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.swipe_layout)
    public SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbarLayout;

    private InfoPresenter presenter;
    private MedicineModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        if (!checkIntent(getIntent())) {
            Log.e(TAG, "onCreate()-> Malformed Intent");
            return;
        }

        model = getModelFromIntent(getIntent());

        ButterKnife.bind(this);
        getDrawer();
        setupView();

        presenter = new InfoPresenterImpl(this);
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Nullable
    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void setupView() {
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary);

        collapsingToolbarLayout.setTitle(model.getName());
    }

    @Override
    public boolean isRefreshing() {
        return swipeRefreshLayout.isRefreshing();
    }

    @Override
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    @Override
    public MedicineModel getModel() {
        return model;
    }

    @Override
    public MedicineModel getModelFromIntent(Intent intent) {
        return ((MedicineModel) intent.getExtras().getSerializable(IntentKeys.OBJECT_MEDICINE_MODEL));
    }

    @Override
    public boolean checkIntent(Intent intent) {
        return !(intent == null || intent.getExtras() == null ||
                !intent.getExtras().containsKey(IntentKeys.OBJECT_MEDICINE_MODEL) ||
                intent.getExtras().getSerializable(IntentKeys.OBJECT_MEDICINE_MODEL) == null);
    }

    @OnClick(R.id.fab_edit)
    @Override
    public void startModelEditActivity() {
        startActivity(new Intent(InfoActivity.this, ModelEditActivity.class)
                .putExtra(IntentKeys.OBJECT_MEDICINE_MODEL, model));
    }
}
