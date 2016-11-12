package com.android.javaeemongodb.ui.presenter.implementation;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.adapter.DocListAdapter;
import com.android.javaeemongodb.ui.presenter.DocListPresenter;
import com.android.javaeemongodb.ui.processor.DocListProcessor;
import com.android.javaeemongodb.ui.processor.implementation.DocListProcessorImpl;
import com.android.javaeemongodb.ui.view.DocListView;

import java.util.ArrayList;

public class DocListPresenterImpl implements DocListPresenter {
    private final String TAG = getClass().getSimpleName();

    private DocListAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<MedicineModel> dataSet;
    private DocListView view;
    private DocListProcessor processor;

    public DocListPresenterImpl(@NonNull DocListView view) {
        this.view = view;
        this.processor = new DocListProcessorImpl();
    }

    @Override
    public void start() {
        dataSet = new ArrayList<>();
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new DocListAdapter(view.getContext(), dataSet);

        setupAdapter(adapter);
        setupRecyclerView(view.getRecyclerView());
        setupProcessor();

        processor.fillDataSet(adapter.getDataSet());
        adapter.notifyDataSetChanged();

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
    public void setupAdapter(DocListAdapter adapter) {
        adapter.setOnClickListener(new DocListAdapter.OnClickListener() {
            @Override
            public void onItemClickListener(MedicineModel model) {

            }
        });
    }

    @Override
    public void setupProcessor() {
        processor.setOnDataReloadListener(new DocListProcessorImpl.OnDataReloadListener() {
            @Override
            public void onLoaded() {
                adapter.notifyDataSetChanged();
                view.setRefreshing(false);
            }
        });
    }
}