package com.android.javaeemongodb.ui.activity;

import android.os.Bundle;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.ui.activity.base.BaseNavDrawerActivity;

import butterknife.ButterKnife;

public class InfoActivity extends BaseNavDrawerActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ButterKnife.bind(this);
        getDrawer();
    }
}
