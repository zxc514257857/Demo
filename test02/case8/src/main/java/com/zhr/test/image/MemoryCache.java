package com.zhr.test.image;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.zhr.test.AppConstants;
import com.zhr.test.util.MD5Utils;

/**
 * Created by zhr on 2018/5/28.
 * Located:zmkj
 * Des:内存缓存工具类
 */
public class MemoryCache {

    private static final String TAG = MemoryCache.class.getSimpleName();
    private LruCache<String , Bitmap> memoryCache;
    private static MemoryCache instance;

    private MemoryCache(){
        int maxMemory = (int) (AppConstants.IMAGE_MAX_MEMORY_CACHE_SIZE);
        memoryCache = new LruCache<String , Bitmap>(maxMemory){
            /**
             * 定义缓存中每项数据的大小
             * @param key
             * @param value
             * @return
             */
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    /**
     * 单例模式保证对象唯一
     */
    public static MemoryCache getInstance() {
        if(instance == null){
            synchronized (MemoryCache.class){
                if(instance == null){
                    instance = new MemoryCache();
                }
            }
        }
        return instance;
    }

    /**
     * 根据url从内存中获取Bitmap
     * @param url
     * @return
     */
    public Bitmap getBitmapFromMemory(String url){
        if(TextUtils.isEmpty(url)){
            Log.i(TAG , "can't get bitmap from memory because url is null");
            return null;
        }
        String key = MD5Utils.hashKeyForDisk(url);
        return memoryCache.get(key);
    }

    /**
     * 根据url将Bitmap设置到内存
     * @param url
     * @param bitmap
     */
    public void setBitmapToMemory(String url , Bitmap bitmap){
        if(TextUtils.isEmpty(url)){
            Log.i(TAG , "can't set bitmap to memory because url is null");
            return;
        }
        String key = MD5Utils.hashKeyForDisk(url);
        if(bitmap == null){
            Log.i(TAG , "can't set bitmap to memory because bitmap is null");
            return;
        }
        memoryCache.put(key , bitmap);
    }

    /**
     * 根据url从内存中删除bitmap
     * @param url
     */
    public void removeBitmapFromMemory(String url){
        if(TextUtils.isEmpty(url)){
            Log.i(TAG , "can't remove bitmap from memory because url is null");
            return;
        }
        String key = MD5Utils.hashKeyForDisk(url);
        memoryCache.remove(key);
    }
}
