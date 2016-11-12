package com.android.javaeemongodb.ui.processor;

import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.processor.implementation.DocListProcessorImpl;

import java.util.ArrayList;

public interface DocListProcessor {
    void fillDataSet(ArrayList<MedicineModel> dataSet);

    void reloadDataSet(ArrayList<MedicineModel> dataSet);

    void setOnDataReloadListener(DocListProcessorImpl.OnDataReloadListener onDataReloadListener);
}
