package com.android.javaeemongodb.ui.processor.implementation;

import android.util.Log;

import com.android.javaeemongodb.data.api.RetrofitAPI;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.presenter.DocListPresenter;
import com.android.javaeemongodb.ui.processor.DocListProcessor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocListProcessorImpl implements DocListProcessor {
    private final String TAG = getClass().getSimpleName();

    private OnDataReloadListener onDataReloadListener;
    private DocListPresenter presenter;

    public DocListProcessorImpl(DocListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void fillDataSet(final ArrayList<MedicineModel> dataSet) {
        RetrofitAPI.getInstance(presenter.getView().getContext()).getModels().enqueue(new Callback<List<MedicineModel>>() {
            @Override
            public void onResponse(Call<List<MedicineModel>> call, Response<List<MedicineModel>> response) {
                if (response == null) {
                    Log.e(TAG, "onResponse()-> Response is null");
                    return;
                }

                if (response.body() == null) {
                    Log.e(TAG, "onResponse()-> Response body is null");
                    return;
                }

                dataSet.addAll(response.body());

                if (onDataReloadListener != null) {
                    onDataReloadListener.onLoaded();
                }
            }

            @Override
            public void onFailure(Call<List<MedicineModel>> call, Throwable t) {
                Log.e(TAG, "onFailure()-> ", t);

                if (onDataReloadListener != null) {
                    onDataReloadListener.onLoaded();
                }
            }
        });
    }

    @Override
    public void reloadDataSet(ArrayList<MedicineModel> dataSet) {
        if (dataSet == null) {
            Log.e(TAG, "reloadDataSet()-> data set is null");
            return;
        }

        dataSet.clear();
        fillDataSet(dataSet);
    }

    public interface OnDataReloadListener {
        void onLoaded();
    }

    public void setOnDataReloadListener(OnDataReloadListener onDataReloadListener) {
        this.onDataReloadListener = onDataReloadListener;
    }
}