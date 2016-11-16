package com.android.javaeemongodb.ui.presenter.implementation;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.InfoItemModel;
import com.android.javaeemongodb.ui.adapter.InfoItemListAdapter;
import com.android.javaeemongodb.ui.presenter.InfoPresenter;
import com.android.javaeemongodb.ui.processor.InfoProcessor;
import com.android.javaeemongodb.ui.processor.implementation.InfoProcessorImpl;
import com.android.javaeemongodb.ui.view.InfoView;

import java.util.ArrayList;

public class InfoPresenterImpl implements InfoPresenter {
    private final String TAG = getClass().getSimpleName();

    private InfoItemListAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<InfoItemModel> dataSet;
    private InfoView view;
    private InfoProcessor processor;

    public InfoPresenterImpl(@NonNull InfoView view) {
        this.view = view;
        this.processor = new InfoProcessorImpl(this);
    }

    @Override
    public void start() {
        dataSet = new ArrayList<>();
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new InfoItemListAdapter(dataSet, view.getContext());

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
    public void setupAdapter(InfoItemListAdapter adapter) {
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
    public InfoItemListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public InfoView getView() {
        return view;
    }
}
