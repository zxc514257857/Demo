package com.zhr.test.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;
import com.zhr.test.AppConstants;
import com.zhr.test.util.FileUtils;
import com.zhr.test.util.MD5Utils;
import com.zhr.test.util.SoftwareUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhr on 2018/5/28.
 * Located:zmkj
 * Des:硬盘缓存工具类
 */
public class DiskCache {

    /**
     * 硬盘缓存最大容量
     */
    private static final String TAG = DiskCache.class.getSimpleName();
    private DiskLruCache diskLruCache;
    private static DiskCache instance;

    private DiskCache(Context context , String diskCacheDirName) {
        try {
            File diskCacheDir = FileUtils.getDiskCacheDir(context, diskCacheDirName);
            if(!diskCacheDir.exists()){
                diskCacheDir.mkdirs();
            }
            diskLruCache = DiskLruCache.open(diskCacheDir , SoftwareUtils.getAppVersion(context), 1, AppConstants.IMAGE_MAX_DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例模式保证对象唯一
     */
    public static DiskCache getInstance(Context context, String diskCacheDirName){
        if(instance == null){
            synchronized (DiskCache.class){
                if(instance == null){
                    if(TextUtils.isEmpty(diskCacheDirName)){
                        Log.i(TAG , "can't create diskCache instance because diskCacheDirName is null");
                        return null;
                    }
                    instance = new DiskCache(context , diskCacheDirName);
                }
            }
        }
        return instance;
    }

    /**
     * 根据url从硬盘中获取Bitmap
     * @param url
     * @return
     */
    public Bitmap getBitmapFromDisk(String url){
        try {
            if(TextUtils.isEmpty(url)){
                Log.i(TAG , "can't get bitmap from disk because url is null");
                return null;
            }
            String key = MD5Utils.hashKeyForDisk(url);
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            if(snapshot != null){
                return BitmapFactory.decodeStream(snapshot.getInputStream(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据url将Bitmap设置到硬盘
     * @param url
     * @param bitmap
     */
    public void setBitmapToDisk(String url , Bitmap bitmap){
        if(TextUtils.isEmpty(url)){
            Log.i(TAG , "can't set bitmap to disk because url is null");
            return;
        }
        String key = MD5Utils.hashKeyForDisk(url);
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(key);
            OutputStream outputStream = editor.newOutputStream(0);
            if(bitmap == null){
                Log.i(TAG , "can't set bitmap to disk because bitmap is null");
                return;
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
