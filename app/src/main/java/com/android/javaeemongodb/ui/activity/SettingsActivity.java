package com.android.javaeemongodb.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.api.RetrofitAPI;
import com.android.javaeemongodb.data.model.SettingsModel;
import com.android.javaeemongodb.helper.SharedPrefUtils;
import com.android.javaeemongodb.ui.activity.base.BaseNavDrawerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseNavDrawerActivity {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.root_view)
    public CoordinatorLayout rootView;

    @BindView(R.id.til_settings_host)
    public TextInputLayout TILHost;

    @BindView(R.id.til_settings_port)
    public TextInputLayout TILPort;

    private SettingsModel activeSettings;
    private SettingsModel oldSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);
        getDrawer();
        setupView();

        setActiveSettings(SettingsModel.getActiveSettings(this));
    }

    private void setupView() {
        TILHost.getEditText().setText(SharedPrefUtils.getInstance(this).getRestApiHost());
        TILPort.getEditText().setText(String.valueOf(SharedPrefUtils.getInstance(this).getRestApiPort()));

        TILHost.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (view instanceof EditText) {
                        ((EditText) view).selectAll();
                    }
                }
            }
        });
    }

    @OnClick(R.id.fab_edit)
    public void saveSettings() {
        SettingsModel settings = new SettingsModel(
                TILHost.getEditText().getText().toString(),
                new SettingsModel.RestApiPort(Integer.valueOf(TILPort.getEditText().getText().toString()))
        );

        setOldSettings(getActiveSettings());
        setActiveSettings(settings);

        Snackbar snackbar = Snackbar.make(rootView, R.string.snack_bar_settings_saved, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.snack_bar_dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActiveSettings(getOldSettings());
            }
        });
        snackbar.show();
    }

    public void setOldSettings(SettingsModel oldSettings) {
        this.oldSettings = oldSettings;

        RetrofitAPI.destroy();
    }

    public void setActiveSettings(SettingsModel activeSettings) {
        this.activeSettings = activeSettings;

        SharedPrefUtils.getInstance(this).setRestApiHost(activeSettings.getRestApiHost());
        SharedPrefUtils.getInstance(this).setRestApiPort(activeSettings.getRestApiPort().getPort());

        TILHost.getEditText().setText(activeSettings.getRestApiHost());
        TILPort.getEditText().setText(activeSettings.getRestApiPort().toString());

        RetrofitAPI.destroy();
    }

    @Nullable
    public SettingsModel getActiveSettings() {
        return activeSettings;
    }

    @Nullable
    public SettingsModel getOldSettings() {
        return oldSettings;
    }
}
