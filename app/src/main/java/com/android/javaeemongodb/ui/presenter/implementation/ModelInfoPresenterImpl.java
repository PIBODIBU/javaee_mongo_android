package com.android.javaeemongodb.ui.presenter.implementation;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.data.model.InfoItemModel;
import com.android.javaeemongodb.ui.adapter.ModelInfoItemListAdapter;
import com.android.javaeemongodb.ui.presenter.ModelInfoPresenter;
import com.android.javaeemongodb.ui.processor.InfoProcessor;
import com.android.javaeemongodb.ui.processor.implementation.InfoProcessorImpl;
import com.android.javaeemongodb.ui.view.ModelInfoView;

import java.util.ArrayList;

public class ModelInfoPresenterImpl implements ModelInfoPresenter {
    private final String TAG = getClass().getSimpleName();

    private ModelInfoItemListAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<InfoItemModel> dataSet;
    private ModelInfoView view;
    private InfoProcessor processor;

    public ModelInfoPresenterImpl(@NonNull ModelInfoView view) {
        this.view = view;
        this.processor = new InfoProcessorImpl(this);
    }

    @Override
    public void start() {
        dataSet = new ArrayList<>();
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new ModelInfoItemListAdapter(dataSet, view.getContext());

        setupAdapter(adapter);
        setupRecyclerView(view.getRecyclerView());
        setupProcessor();

        processor.setDocumentId(view.getModel().getId());
        processor.fillDataSet(adapter.getDataSet());

        view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                view.setRefreshing(true);
                processor.reloadDataSet(adapter.getDataSet());
            }
        });
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setupAdapter(ModelInfoItemListAdapter adapter) {
        adapter.setOnItemClickListener(new ModelInfoItemListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(InfoItemModel model) {

            }

            @Override
            public void onItemLongClick(InfoItemModel model) {

            }
        });
    }

    @Override
    public void setupProcessor() {
        processor.setOnDataReloadListener(new InfoProcessorImpl.OnDataReloadListener() {
            @Override
            public void onLoaded() {
                adapter.notifyDataSetChanged();
                view.setRefreshing(false);
            }
        });
    }

    @Override
    public ModelInfoItemListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public ModelInfoView getView() {
        return view;
    }
}
