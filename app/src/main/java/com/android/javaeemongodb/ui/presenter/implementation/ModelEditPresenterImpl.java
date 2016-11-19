package com.android.javaeemongodb.ui.presenter.implementation;

import android.util.Log;

import com.android.javaeemongodb.data.api.RetrofitAPI;
import com.android.javaeemongodb.data.model.ErrorModel;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.presenter.ModelEditPresenter;
import com.android.javaeemongodb.ui.view.ModelEditView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelEditPresenterImpl implements ModelEditPresenter {
    private final String TAG = getClass().getSimpleName();

    private ModelEditView view;

    public ModelEditPresenterImpl(ModelEditView view) {
        this.view = view;
    }

    @Override
    public void updateModel() {
        MedicineModel model = view.getModelFromUI();
        RetrofitAPI.getInstance(view.getContext()).updateModel(
                model.getId(),
                model.getName(),
                model.getIndication(),
                model.getContraindication(),
                model.getSalesForm()
        ).enqueue(new Callback<ErrorModel>() {
            @Override
            public void onResponse(Call<ErrorModel> call, Response<ErrorModel> response) {
                if (response == null || response.body() == null) {
                    Log.e(TAG, "onResponse()-> Failed");
                    view.onUpdateFailed();
                }

                view.onUpdateSuccess();
            }

            @Override
            public void onFailure(Call<ErrorModel> call, Throwable t) {
                Log.e(TAG, "onFailure()-> ", t);
                view.onUpdateFailed();
            }
        });
    }
}
