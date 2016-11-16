package com.android.javaeemongodb.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.javaeemongodb.R;

import butterknife.ButterKnife;

public class ModelAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_model_add);
        ButterKnife.bind(this);
    }
}
