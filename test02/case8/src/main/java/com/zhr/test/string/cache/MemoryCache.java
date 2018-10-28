package com.zhr.test.string.cache;

import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.zhr.test.AppConstants;
import com.zhr.test.util.MD5Utils;

/**
 * Created by zhr on 2018/5/29.
 * Located:zmkj
 * Des:通过lruCache实现内存缓存
 */
public class MemoryCache {

    private static final String TAG = MemoryCache.class.getSimpleName();
    private LruCache<String , String> memoryCache;
    private static MemoryCache instance;

    /**
     * 单例模式 保证对象唯一
     * @return
     */
    public static MemoryCache getInstance(){
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
     * 私有构造方法 只能通过getInstance创建对象
     */
    private MemoryCache() {
        memoryCache = new LruCache<String , String>((int)AppConstants.STRING_MAX_MEMORY_CACHE_SIZE){
            /**
             * 定义缓存中每项数据的大小
             * @param key
             * @param value
             * @return
             */
            @Override
            protected int sizeOf(String key, String value) {
                return value.length() / 1024;
            }
        };
    }

    /**
     * 从内存中获取字符串
     * @param key
     * @return
     */
    public String getStringFromMemory(String key){
        if (TextUtils.isEmpty(key)){
            Log.i(TAG , "can't get string from memory because key is null");
            return null;
        }
        String md5Key = MD5Utils.hashKeyForDisk(key);
        return memoryCache.get(md5Key);
    }

    /**
     * 根据key将字符串设置到内存
     * @param key
     * @param value
     */
    public void setStringToMemory(String key , String value){
        if (TextUtils.isEmpty(key)||TextUtils.isEmpty(value)){
            Log.i(TAG , "can't set string to memory because key or value is null");
            return;
        }
        String md5Key = MD5Utils.hashKeyForDisk(key);
        memoryCache.put(md5Key , value);
    }

    /**
     * 根据key从内存中删除字符串
     * @param key
     */
    public void removeMemoryCache(String key){
        if(TextUtils.isEmpty(key)){
            Log.i(TAG , "can't remove memoryCache because key is null");
            return;
        }
        String md5Key = MD5Utils.hashKeyForDisk(key);
        memoryCache.remove(md5Key);
    }
}
