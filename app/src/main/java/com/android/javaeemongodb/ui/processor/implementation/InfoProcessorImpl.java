package com.android.javaeemongodb.ui.processor.implementation;

import android.util.Log;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.api.RetrofitAPI;
import com.android.javaeemongodb.data.model.InfoItemModel;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.presenter.InfoPresenter;
import com.android.javaeemongodb.ui.processor.InfoProcessor;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoProcessorImpl implements InfoProcessor {
    private final String TAG = getClass().getSimpleName();

    private InfoProcessorImpl.OnDataReloadListener onDataReloadListener;
    private String docId = "";
    private InfoPresenter presenter;

    public InfoProcessorImpl(InfoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void fillDataSet(final ArrayList<InfoItemModel> dataSet) {
        if (docId.equals("")) {
            Log.e(TAG, "fillDataSet()-> Document id is not set. See InfoProcessorImpl#setDocumentId()");
            return;
        }

        RetrofitAPI.getInstance().getMedicine(docId).enqueue(new Callback<MedicineModel>() {
            @Override
            public void onResponse(Call<MedicineModel> call, Response<MedicineModel> response) {
                if (response == null) {
                    Log.e(TAG, "onResponse()-> Response is null");
                    return;
                }

                if (response.body() == null) {
                    Log.e(TAG, "onResponse()-> Response body is null");
                    return;
                }

                MedicineModel model = response.body();

                dataSet.add(new InfoItemModel(
                        R.drawable.ic_view_list_grey_500_24dp,
                        model.getIdTitle(),
                        model.getId()
                ));

                dataSet.add(new InfoItemModel(
                        R.drawable.ic_info_outline_grey_500_24dp,
                        model.getNameTitle(),
                        model.getName()
                ));

                dataSet.add(new InfoItemModel(
                        R.drawable.ic_help_outline_grey_500_24dp,
                        model.getIndicationTitle(),
                        model.getIndication()
                ));

                dataSet.add(new InfoItemModel(
                        R.drawable.ic_highlight_off_grey_500_24dp,
                        model.getContraindicationTitle(),
                        model.getContraindication()
                ));

                dataSet.add(new InfoItemModel(
                        R.drawable.ic_add_shopping_cart_grey_500_24dp,
                        model.getSalesFormTitle(),
                        model.getSalesForm()
                ));

                presenter.getAdapter().notifyDataSetChanged();

                if (onDataReloadListener != null) {
                    onDataReloadListener.onLoaded();
                }
            }

            @Override
            public void onFailure(Call<MedicineModel> call, Throwable t) {
                Log.e(TAG, "onFailure()-> ", t);

                if (onDataReloadListener != null) {
                    onDataReloadListener.onLoaded();
                }
            }
        });
    }

    @Override
    public void reloadDataSet(ArrayList<InfoItemModel> dataSet) {
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

    @Override
    public void setOnDataReloadListener(InfoProcessorImpl.OnDataReloadListener onDataReloadListener) {
        this.onDataReloadListener = onDataReloadListener;
    }

    @Override
    public void setDocumentId(String docId) {
        this.docId = docId;
    }
}
