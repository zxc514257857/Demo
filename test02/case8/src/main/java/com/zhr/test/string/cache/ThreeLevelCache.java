package com.zhr.test.string.cache;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhr.test.bean.DataBean;
import com.zhr.test.string.OnRequestListener;

public class ThreeLevelCache {

    private static final String TAG = ThreeLevelCache.class.getSimpleName();
    private static ThreeLevelCache instance;
    private final NetCache mNetCache;
    private final MemoryCache mMemoryCache;
    private final DiskCache mDiskCache;

    public static ThreeLevelCache getInstance(Context context){
        if(instance == null){
            synchronized (ThreeLevelCache.class){
                if(instance == null){
                    instance = new ThreeLevelCache(context);
                }
            }
        }
        return instance;
    }

    private ThreeLevelCache(Context context){
        mNetCache = NetCache.getInstance();
        mMemoryCache = MemoryCache.getInstance();
        mDiskCache = DiskCache.getInstance(context);
    }

    public void showTextView(final Context context , final String key , final TextView textView){

        if(TextUtils.isEmpty(key)){
            Log.i(TAG , "can't show imageView because url is null");
            return;
        }

        if(textView == null){
            Log.i(TAG , "can't show textView because textView is null");
            return;
        }

        mNetCache.getData(new OnRequestListener<DataBean>() {

            /**
             * 先从网络中获取，获取失败之后再从内存获取或者从硬盘获取
             * @param response
             */
            @Override
            public void onsuccess(DataBean response) {
                String data = JSON.toJSONString(response);
                if(TextUtils.isEmpty(data)){
                    Log.i(TAG , "can't get data from netCache because data is null");
                    return;
                }
                textView.setText(data);
                if(data.equals(mMemoryCache.getStringFromMemory(key))){
                    Log.i(TAG, "no need to set netCache to memory , because it’s equal");
                }else{
                    mMemoryCache.setStringToMemory(key , data);
                    Log.i(TAG, "net cache string set to memory success!");
                }

                if(data.equals(mDiskCache.getStringFromDisk(context , key , ""))){
                    Log.i(TAG, "no need to set netCache to disk , because it’s equal");
                }else{
                    mDiskCache.setStringToDisk(context , key , data);
                    Log.i(TAG, "net cache string set to disk success!");
                }
            }

            @Override
            public void onFail(String errCode, String errMsg) {
                String stringFromMemory = mMemoryCache.getStringFromMemory(key);
                if(stringFromMemory != null){
                    textView.setText(stringFromMemory);
                    Log.i(TAG, "show textView from memory success!");
                    return;
                }
                String stringFromDisk = mDiskCache.getStringFromDisk(context, key, "");
                if(stringFromDisk != null){
                    textView.setText(stringFromDisk);
                    Log.i(TAG, "show textView from disk success!");
                    mMemoryCache.setStringToMemory(key , stringFromDisk);
                    Log.i(TAG, "disk cache string set to memory success!");
                    return;
                }
            }
        });
    }
}
