package com.android.javaeemongodb.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.ui.activity.base.BaseNavDrawerActivity;
import com.android.javaeemongodb.ui.presenter.DocListPresenter;
import com.android.javaeemongodb.ui.presenter.implementation.DocListPresenterImpl;
import com.android.javaeemongodb.ui.view.DocListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DocListActivity extends BaseNavDrawerActivity implements DocListView {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.swipe_layout)
    public SwipeRefreshLayout refreshLayout;

    private DocListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_list);

        ButterKnife.bind(this);
        getDrawer();

        setupView();

        presenter = new DocListPresenterImpl(this);
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setupView() {
        refreshLayout.setColorSchemeResources(
                R.color.colorPrimary);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Nullable
    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public boolean isRefreshing() {
        return refreshLayout.isRefreshing();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        refreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        refreshLayout.setOnRefreshListener(onRefreshListener);
    }
}
