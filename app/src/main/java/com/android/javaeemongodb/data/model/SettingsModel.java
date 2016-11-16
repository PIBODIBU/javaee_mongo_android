package com.android.javaeemongodb.data.model;

import android.content.Context;

import com.android.javaeemongodb.helper.SharedPrefUtils;

public class SettingsModel {
    private String restApiHost;
    private RestApiPort restApiPort;

    public SettingsModel(String restApiHost, RestApiPort restApiPort) {
        this.restApiHost = restApiHost;
        this.restApiPort = restApiPort;
    }

    public String getRestApiHost() {
        return restApiHost;
    }

    public RestApiPort getRestApiPort() {
        return restApiPort;
    }

    public static SettingsModel getActiveSettings(Context context) {
        return new SettingsModel(
                SharedPrefUtils.getInstance(context).getRestApiHost(),
                SharedPrefUtils.getInstance(context).getRestApiPort());
    }

    public static class RestApiPort {
        private Integer port;

        public RestApiPort(Integer port) {
            this.port = port;
        }

        public Integer getPort() {
            return port;
        }

        @Override
        public String toString() {
            return String.valueOf(getPort());
        }
    }
}
