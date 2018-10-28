package com.zhr.test.string.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.zhr.test.AppConstants;

/**
 * Created by zhr on 2018/5/29.
 * Located:zmkj
 * Des:通过SP实现diskCache
 */
public class DiskCache {

    private static final String TAG = DiskCache.class.getSimpleName();
    private static DiskCache instance;
    private final SharedPreferences mSp;

    /**
     * 单例模式 保证对象唯一
     * @return
     */
    public static DiskCache getInstance(Context context){
        if(instance == null){
            synchronized (DiskCache.class){
                if(instance == null){
                    instance = new DiskCache(context);
                }
            }
        }
        return instance;
    }

    /**
     * 私有构造方法
     * @param context
     */
    private DiskCache(Context context) {
        mSp = context.getSharedPreferences(AppConstants.DISK_CACHE_STR, Context.MODE_PRIVATE);
        // 测试了追加方式  没什么卵用  根本不会在文件后追加内容
//        mSp = context.getSharedPreferences(AppConstants.DISK_CACHE_STR, Context.MODE_APPEND);
    }

    /**
     * 根据key保存字符串至硬盘
     * @param context
     * @param key
     * @param value
     */
    public void setStringToDisk(Context context , String key , String value){
        if(TextUtils.isEmpty(key) || TextUtils.isEmpty(value)){
            Log.i(TAG , "can't set string to disk because key or value is null");
            return;
        }
        SharedPreferences.Editor editor = DiskCache.getInstance(context).mSp.edit();
        editor.putString(key , value);
        editor.commit();
    }

    /**
     * 根据key保存int至硬盘
     * @param context
     * @param key
     * @param value
     */
    public void setIntToDisk(Context context , String key , int value){
        if(TextUtils.isEmpty(key)){
            Log.i(TAG , "can't set int to disk because key is null");
            return;
        }
        SharedPreferences.Editor editor = DiskCache.getInstance(context).mSp.edit();
        editor.putInt(key , value);
        editor.commit();
    }

    /**
     * 根据key保存boolean至硬盘
     * @param context
     * @param key
     * @param value
     */
    public void setBooleanToDisk(Context context , String key , boolean value){
        if(TextUtils.isEmpty(key)){
            Log.i(TAG , "can't set boolean to disk because key is null");
            return;
        }
        SharedPreferences.Editor editor = DiskCache.getInstance(context).mSp.edit();
        editor.putBoolean(key , value);
        editor.commit();
    }

    /**
     * 根据key从硬盘中获取字符串
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public String getStringFromDisk(Context context , String key , String defaultValue){
        if(TextUtils.isEmpty(key)){
            Log.i(TAG , "can't get string from disk because key is null !");
            return null;
        }
        return DiskCache.getInstance(context).mSp.getString(key , defaultValue);
    }

    /**
     * 根据key从硬盘中获取int
     * @param context
     * @param key
     * @param defaultInt
     * @return
     */
    public int getIntFromDisk(Context context , String key , int defaultInt){
        if(TextUtils.isEmpty(key)){
            Log.i(TAG , "can't get int from disk because key is null");
            return 0;
        }
        return DiskCache.getInstance(context).mSp.getInt(key , defaultInt);
    }

    /**
     * 根据key从硬盘中获取boolean
     * @param context
     * @param key
     * @param defaultBoolean
     * @return
     */
    public boolean getBooleanFromDisk(Context context , String key , boolean defaultBoolean){
        if(TextUtils.isEmpty(key)){
            Log.i(TAG , "can't get boolean from disk because key is null");
            return false;
        }
        return DiskCache.getInstance(context).mSp.getBoolean(key , defaultBoolean);
    }

    /**
     * 根据key从硬盘中删除缓存
     * @param context
     * @param key
     */
    public void removeDiskCache(Context context , String key){
        if(TextUtils.isEmpty(key)){
            Log.i(TAG , "can't remove diskCache because key is null");
            return;
        }
        SharedPreferences.Editor editor = DiskCache.getInstance(context).mSp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除硬盘缓存
     * @param context
     */
    public void clearDiskCache(Context context){
        SharedPreferences.Editor editor = DiskCache.getInstance(context).mSp.edit();
        editor.clear();
        editor.commit();
    }
}
