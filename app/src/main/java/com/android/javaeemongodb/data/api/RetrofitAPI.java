package com.android.javaeemongodb.data.api;

import android.content.Context;
import android.util.Log;

import com.android.javaeemongodb.helper.SharedPrefUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    private static final String TAG = "RetrofitAPI";

    private static String BASE_URL = "";
    private static IAPIService api = null;

    public static IAPIService getInstance(Context context) {
        BASE_URL = "http://" + SharedPrefUtils.getInstance(context).getRestApiHost() +
                ":" + SharedPrefUtils.getInstance(context).getRestApiPort().toString() + "/api/v1/";

        if (api == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .retryOnConnectionFailure(true)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(IAPIService.class);
        }

        return api;
    }

    public static void destroy() {
        api = null;
    }
}
