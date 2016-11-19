package com.android.javaeemongodb.ui.processor;

import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.processor.implementation.DocumentListProcessorImpl;

import java.util.ArrayList;

public interface DocumentListProcessor {
    void fillDataSet(ArrayList<MedicineModel> dataSet);

    void reloadDataSet(ArrayList<MedicineModel> dataSet);

    void setOnDataReloadListener(DocumentListProcessorImpl.OnDataReloadListener onDataReloadListener);

    void deleteModel(MedicineModel model);
}
