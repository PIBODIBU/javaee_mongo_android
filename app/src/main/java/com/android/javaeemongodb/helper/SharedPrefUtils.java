package com.android.javaeemongodb.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.javaeemongodb.data.api.RetrofitAPI;
import com.android.javaeemongodb.data.model.SettingsModel;

public class SharedPrefUtils {
    private static final String SHARED_PREFERENCES_SETTING = "com.android.javaeemongodb";
    private static final String KEY_REST_API_HOST = "rest_api_host";
    private static final String KEY_REST_API_PORT = "rest_api_port";
    public static final Integer DEFAULT_REST_API_PORT = 8080;
    public static final String DEFAULT_REST_API_HOST = "192.168.1.1";

    private static SharedPrefUtils instance;
    private SharedPreferences sharedPreferences;

    public SharedPrefUtils(final Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_SETTING, Context.MODE_PRIVATE);
    }

    public static SharedPrefUtils getInstance(Context context) {
        return instance == null ? new SharedPrefUtils(context) : instance;
    }

    public SharedPrefUtils setRestApiHost(String host) {
        sharedPreferences.edit().putString(KEY_REST_API_HOST, host).apply();
        return this;
    }

    public SharedPrefUtils setRestApiPort(int port) {
        sharedPreferences.edit().putInt(KEY_REST_API_PORT, port).apply();
        return this;
    }

    public String getRestApiHost() {
        try {
            return sharedPreferences.getString(KEY_REST_API_HOST, DEFAULT_REST_API_HOST);
        } catch (Exception ex) {
            return DEFAULT_REST_API_HOST;
        }
    }

    public SettingsModel.RestApiPort getRestApiPort() {
        try {
            return new SettingsModel.RestApiPort(sharedPreferences.getInt(KEY_REST_API_PORT, DEFAULT_REST_API_PORT));
        } catch (Exception ex) {
            return new SettingsModel.RestApiPort(DEFAULT_REST_API_PORT);
        }
    }
}
