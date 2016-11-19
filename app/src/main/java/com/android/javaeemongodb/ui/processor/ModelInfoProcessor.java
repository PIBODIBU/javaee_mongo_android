package com.android.javaeemongodb.ui.processor;

import com.android.javaeemongodb.data.model.InfoItemModel;
import com.android.javaeemongodb.ui.processor.implementation.ModelInfoProcessorImpl;

import java.util.LinkedList;

public interface ModelInfoProcessor {
    void fillDataSet(LinkedList<InfoItemModel> dataSet);

    void reloadDataSet(LinkedList<InfoItemModel> dataSet);

    void setOnDataReloadListener(ModelInfoProcessorImpl.OnDataReloadListener onDataReloadListener);

    void setDocumentId(String docId);
}
