package com.zhr.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DisplayPicByUrlModelImpl implements DisplayPicByUrlModel {

    @Override
    public Bitmap getBitmapByUrl(Context context , String path , String method , int timeout) {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setConnectTimeout(timeout);
            int responseCode = conn.getResponseCode();
            if(responseCode == AppConstance.INTERNET_REQUEST_SUCCESS){
                InputStream inputStream = conn.getInputStream();
                return BitmapFactory.decodeStream(inputStream);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context , "获取图片资源失败！" , Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
