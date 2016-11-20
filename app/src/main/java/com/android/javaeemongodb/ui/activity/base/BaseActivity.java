package com.android.javaeemongodb.ui.activity.base;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.android.javaeemongodb.R;
import com.r0adkll.slidr.Slidr;

public abstract class BaseActivity extends AppCompatActivity {
    protected void attachSlider() {
        int primary = ContextCompat.getColor(this, R.color.colorPrimary);
        int secondary = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        Slidr.attach(this, primary, secondary);
    }
}
