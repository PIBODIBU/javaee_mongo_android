package com.android.javaeemongodb.ui.processor;

import com.android.javaeemongodb.data.model.InfoItemModel;
import com.android.javaeemongodb.ui.processor.implementation.InfoProcessorImpl;

import java.util.ArrayList;

public interface InfoProcessor {
    void fillDataSet(ArrayList<InfoItemModel> dataSet);

    void reloadDataSet(ArrayList<InfoItemModel> dataSet);

    void setOnDataReloadListener(InfoProcessorImpl.OnDataReloadListener onDataReloadListener);

    void setDocumentId(String docId);
}
