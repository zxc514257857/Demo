package com.zhr.test.video;

import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
import com.zhr.test.AppApplication;

public class DiskCache {

    private static DiskCache instance;

    public static DiskCache getInstance(){
        if (instance == null){
            synchronized (DiskCache.class){
                if(instance == null){
                    instance = new DiskCache();
                }
            }
        }
        return instance;
    }

    private DiskCache(){}

    public String getProxyUrl(Context context , String url){
        HttpProxyCacheServer proxy = AppApplication.getProxy(context);
        return proxy.getProxyUrl(url);
    }
}
