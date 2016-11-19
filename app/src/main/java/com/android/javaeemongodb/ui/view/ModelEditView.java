package com.android.javaeemongodb.ui.view;

import android.content.Context;
import android.content.Intent;

import com.android.javaeemongodb.data.model.MedicineModel;

public interface ModelEditView {
    void setupView();

    boolean checkIntent(Intent intent);

    MedicineModel getModelFromIntent(Intent intent);

    MedicineModel getModelFromUI();

    Context getContext();

    void onUpdateSuccess();

    void onUpdateFailed();
}
