package com.zhr.test.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhr on 2018/5/28.
 * Located:zmkj
 * Des:网络缓存工具类
 */
public class NetCache {

    private static final String TAG = NetCache.class.getSimpleName();
    private static NetCache instance;

    /**
     * 单例模式保证对象唯一
     */
    public static NetCache getInstance(){
        if(instance == null){
            synchronized (NetCache.class){
                if(instance == null){
                    instance = new NetCache();
                }
            }
        }
        return instance;
    }

    /**
     * 根据url从网络上获取Bitmap
     * @param imageUrl
     * @return
     */
    public Bitmap downloadBitmap(String imageUrl){

        if(TextUtils.isEmpty(imageUrl)){
            Log.i(TAG , "can't download bitmap because imageUrl is null");
            return null;
        }

        HttpURLConnection conn = null;

        try {
            URL url = new URL(imageUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.connect();
            // 返回码为200 表示服务器成功处理请求
            // 404 表示请求网页不存在
            // 503 表示服务不可用
            if(conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
                // 获取原图的Bitmap
                return getOriginalBitmap(inputStream);
                // 获取压缩图的Bitmap
//                return getCompressionBitmap(inputStream);
            }else{
                Log.i(TAG, "图片资源从服务器中获取失败！");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                // 断开连接
                conn.disconnect();
            }
        }
        return null;
    }

    /**
     * 获取压缩图的Bitmap
     * @param inputStream
     * @return
     */
    private Bitmap getCompressionBitmap(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 图片分辨率为原图的一半
        options.inSampleSize = 2;
        return BitmapFactory.decodeStream(inputStream , null , options);
    }

    /**
     * 获取原图的Bitmap
     * @param inputStream
     */
    private Bitmap getOriginalBitmap(InputStream inputStream) {
        // 解码
        return BitmapFactory.decodeStream(inputStream);
    }
}
