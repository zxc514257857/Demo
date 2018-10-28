package com.zhr.test;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
import com.zhr.test.util.FileUtils;

public class AppApplication extends Application{

    private static AppApplication instance;
    private HttpProxyCacheServer proxy;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppApplication getInstance(){
        return instance;
    }

    public static HttpProxyCacheServer getProxy(Context context) {
        AppApplication app = (AppApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .cacheDirectory(FileUtils.getDiskCacheDir(this, AppConstants.DISK_CACHE_VIDEO))
                .maxCacheSize(AppConstants.VIDEO_MAX_DISK_CACHE_SIZE)
                .maxCacheFilesCount(AppConstants.VIDEO_MAX_DISK_CACHE_FILES_COUNT)
                .build();
    }
}
