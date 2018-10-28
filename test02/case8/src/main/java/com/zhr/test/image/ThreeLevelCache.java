package com.zhr.test.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by zhr on 2018/5/28.
 * Located:zmkj
 * Des:三级缓存
 */
public class ThreeLevelCache {

    private static final String TAG = ThreeLevelCache.class.getSimpleName();
    private static ThreeLevelCache instance;
    private NetCache mNetCache = null;
    private MemoryCache mMemoryCache = null;
    private DiskCache mDiskCache = null;
    private Bitmap mBitmap = null;

    private ThreeLevelCache(Context context, String diskCacheDirName) {
        mNetCache = NetCache.getInstance();
        mMemoryCache = MemoryCache.getInstance();
        mDiskCache = DiskCache.getInstance(context, diskCacheDirName);
    }

    /**
     * 单例模式保证对象唯一
     */
    public static ThreeLevelCache getInstance(Context context, String diskCacheDirName) {
        if (instance == null) {
            synchronized (ThreeLevelCache.class) {
                if (instance == null) {
                    if(TextUtils.isEmpty(diskCacheDirName)){
                        Log.i(TAG , "can't create threeLevelCache instance because diskCacheDirName is null");
                        return null;
                    }
                    instance = new ThreeLevelCache(context, diskCacheDirName);
                }
            }
        }
        return instance;
    }

    /**
     * 显示图片
     *
     * @param url
     * @param imageView
     */
    public void showImageView(final String url, final ImageView imageView) {
        if(TextUtils.isEmpty(url)){
            Log.i(TAG , "can't show imageView because url is null");
            return;
        }

        if(imageView == null){
            Log.i(TAG , "can't show imageView because imageView is null");
            return;
        }

        mBitmap = mMemoryCache.getBitmapFromMemory(url);
        if (mBitmap != null) {
            imageView.setImageBitmap(mBitmap);
            Log.i(TAG, "show imageView from memory success!");
            return;
        }

        mBitmap = mDiskCache.getBitmapFromDisk(url);
        if (mBitmap != null) {
            imageView.setImageBitmap(mBitmap);
            Log.i(TAG, "show imageView from disk success!");
            mMemoryCache.setBitmapToMemory(url, mBitmap);
            Log.i(TAG, "disk cache bitmap set to memory success!");
            return;
        }

        /**
         * 通过AsyncTask实现线程切换
         */
        @SuppressLint("StaticFieldLeak")
        AsyncTask<String, Integer, String> asyncTask = new AsyncTask<String, Integer, String>() {

            /**
             * 子线程中执行后台任务
             * @param params
             * @return
             */
            @Override
            protected String doInBackground(String... params) {
                mBitmap = mNetCache.downloadBitmap(url);
                return null;
            }

            /**
             * 执行完后台任务后更新UI
             * @param result
             */
            @Override
            protected void onPostExecute(String result) {
                if (mBitmap != null) {
                    // 更新UI需要在主线程中执行
                    imageView.setImageBitmap(mBitmap);
                    Log.i(TAG, "show imageView from net success!");
                    mMemoryCache.setBitmapToMemory(url, mBitmap);
                    Log.i(TAG, "net cache bitmap set to memory success!");
                    mDiskCache.setBitmapToDisk(url, mBitmap);
                    Log.i(TAG, "net cache bitmap set to disk success!");
                }
                super.onPostExecute(result);
            }
        };
        asyncTask.execute(url);

        /**
         * 最简单的线程切换
         */
//        ThreadUtils.runInThread(new Runnable() {
//            @Override
//            public void run() {
//                // 访问网络 需要在子线程中执行
//                mBitmap = mNetCacheUtil.downloadBitmap(url);
//                ThreadUtils.runUnThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (mBitmap != null) {
//                            // 更新UI需要在主线程中执行
//                            imageView.setImageBitmap(mBitmap);
//                            Log.i(TAG, "show imageView from net success!");
//                            mMemoryCacheUtil.setBitmapToMemory(url, mBitmap);
//                            Log.i(TAG, "disk cache bitmap set to memory success!");
//                            mDiskCacheUtil.setBitmapToDisk(url, mBitmap);
//                            Log.i(TAG, "disk cache bitmap set to disk success!");
//                        }
//                    }
//                });
//            }
//        });
    }
}
