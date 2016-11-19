package com.android.javaeemongodb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.helper.IntentKeys;
import com.android.javaeemongodb.ui.activity.base.BaseNavDrawerActivity;
import com.android.javaeemongodb.ui.presenter.ModelInfoPresenter;
import com.android.javaeemongodb.ui.presenter.implementation.ModelInfoPresenterImpl;
import com.android.javaeemongodb.ui.view.ModelInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModelInfoActivity extends BaseNavDrawerActivity implements ModelInfoView {
    private final String TAG = getClass().getSimpleName();

    private final int REQUEST_MODEL_EDIT = 1;
    public static final int RESULT_MODEL_EDITED = 1;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.swipe_layout)
    public SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbarLayout;

    private ModelInfoPresenter presenter;
    private MedicineModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_info);

        if (!checkIntent(getIntent())) {
            Log.e(TAG, "onCreate()-> Malformed Intent");
            return;
        }

        model = getModelFromIntent(getIntent());

        ButterKnife.bind(this);
        getDrawer();
        setupView();

        presenter = new ModelInfoPresenterImpl(this);
        presenter.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_MODEL_EDIT) {
            if (resultCode == RESULT_OK) {
                if (!data.getExtras().containsKey(IntentKeys.OBJECT_MEDICINE_MODEL)) {
                    Log.e(TAG, "onActivityResult()-> No data for UI update");
                    return;
                }

                MedicineModel model = ((MedicineModel) data.getExtras().getSerializable(IntentKeys.OBJECT_MEDICINE_MODEL));
                if (model == null) {
                    Log.e(TAG, "onActivityResult()-> Model is null");
                    return;
                }

                onModelEdited(model);
            }
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onModelEdited(@NonNull MedicineModel model) {
        this.model = model;
        setResult(RESULT_MODEL_EDITED);
        updateUI();
    }

    @Override
    public void updateUI() {
        collapsingToolbarLayout.setTitle(getModel().getName());
        presenter.refillDataSet();
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
        startActivityForResult(new Intent(ModelInfoActivity.this, ModelEditActivity.class)
                .putExtra(IntentKeys.OBJECT_MEDICINE_MODEL, model), REQUEST_MODEL_EDIT);
    }

    @Override
    public void setModel(MedicineModel model) {
        this.model = model;
    }
}
