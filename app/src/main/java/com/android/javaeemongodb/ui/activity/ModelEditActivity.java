package com.android.javaeemongodb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSwitcher;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.helper.IntentKeys;
import com.android.javaeemongodb.ui.activity.base.BaseNavDrawerActivity;
import com.android.javaeemongodb.ui.presenter.ModelEditPresenter;
import com.android.javaeemongodb.ui.presenter.implementation.ModelEditPresenterImpl;
import com.android.javaeemongodb.ui.view.ModelEditView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModelEditActivity extends BaseNavDrawerActivity implements ModelEditView {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.root_view)
    public CoordinatorLayout coordinatorLayout;

    @BindView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.til_model_name)
    public TextInputLayout TILModelName;

    @BindView(R.id.til_model_indication)
    public TextInputLayout TILIndication;

    @BindView(R.id.til_model_contraindication)
    public TextInputLayout TILContraIndication;

    @BindView(R.id.til_model_sales_form)
    public TextInputLayout TILSalesForm;

    private MedicineModel model;

    private ModelEditPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_edit);

        if (!checkIntent(getIntent())) {
            Log.e(TAG, "onCreate()-> Model is not set");
            return;
        }

        model = getModelFromIntent(getIntent());
        presenter = new ModelEditPresenterImpl(this);

        ButterKnife.bind(this);
        getDrawer();

        setupView();
    }

    @Override
    public void setupView() {
        if (TILModelName.getEditText() != null)
            TILModelName.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    collapsingToolbarLayout.setTitle(s.toString());
                }
            });

        collapsingToolbarLayout.setTitle(model.getName());
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));

        TILModelName.getEditText().setText(model.getName());
        TILIndication.getEditText().setText(model.getIndication());
        TILContraIndication.getEditText().setText(model.getContraindication());
        TILSalesForm.getEditText().setText(model.getSalesForm());
    }

    @Override
    public boolean checkIntent(Intent intent) {
        return !(intent == null || intent.getExtras() == null ||
                !intent.getExtras().containsKey(IntentKeys.OBJECT_MEDICINE_MODEL) ||
                intent.getExtras().getSerializable(IntentKeys.OBJECT_MEDICINE_MODEL) == null);
    }

    @Override
    public MedicineModel getModelFromIntent(Intent intent) {
        return ((MedicineModel) intent.getExtras().getSerializable(IntentKeys.OBJECT_MEDICINE_MODEL));
    }

    @Override
    public MedicineModel getModelFromUI() {
        return new MedicineModel(
                model.getId(),
                TILModelName.getEditText().getText().toString(),
                "",
                TILIndication.getEditText().getText().toString(),
                TILContraIndication.getEditText().getText().toString(),
                TILSalesForm.getEditText().getText().toString()
        );
    }

    @OnClick(R.id.fab_update)
    public void updateModel() {
        presenter.updateModel();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onUpdateSuccess() {
        setResult(RESULT_OK, new Intent().putExtra(IntentKeys.OBJECT_MEDICINE_MODEL, getModelFromUI()));
        finish();
    }

    @Override
    public void onUpdateFailed() {
        Snackbar.make(coordinatorLayout, R.string.model_update_failed, Snackbar.LENGTH_LONG).show();
    }
}
