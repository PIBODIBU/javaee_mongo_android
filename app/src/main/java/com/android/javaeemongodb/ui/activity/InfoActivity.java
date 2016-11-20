package com.android.javaeemongodb.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.api.RetrofitAPI;
import com.android.javaeemongodb.data.model.ServerInfoModel;
import com.android.javaeemongodb.ui.activity.base.BaseNavDrawerActivity;
import com.android.javaeemongodb.ui.adapter.InfoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends BaseNavDrawerActivity {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.root_view)
    public CoordinatorLayout coordinatorLayout;

    @BindView(R.id.collapsing_toolbar)
    public CollapsingToolbarLayout toolbarLayout;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private ServerInfoModel model;
    private LinearLayoutManager layoutManager;
    private InfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        getDrawer();
        loadModel();
        setupView();
    }

    private void setupView() {
        toolbarLayout.setTitle(this.getTitle().toString());
    }

    private void setupRecyclerView() {
        adapter = new InfoAdapter(model.toArrayList());
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadModel() {
        RetrofitAPI.getInstance(this).getServerInfo().enqueue(new Callback<ServerInfoModel>() {
            @Override
            public void onResponse(Call<ServerInfoModel> call, Response<ServerInfoModel> response) {
                if (response == null || response.body() == null) {
                    Log.e(TAG, "onResponse()-> Failed");
                    return;
                }

                model = response.body();
                setupRecyclerView();
            }

            @Override
            public void onFailure(Call<ServerInfoModel> call, Throwable t) {
                Log.e(TAG, "onFailure()-> ", t);
            }
        });
    }
}
