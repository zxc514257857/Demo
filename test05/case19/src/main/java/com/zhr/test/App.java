package com.zhr.test;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(AppConstance.TIME_OUT , TimeUnit.MILLISECONDS)
                .readTimeout(AppConstance.TIME_OUT , TimeUnit.MILLISECONDS)
                .writeTimeout(AppConstance.TIME_OUT , TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
