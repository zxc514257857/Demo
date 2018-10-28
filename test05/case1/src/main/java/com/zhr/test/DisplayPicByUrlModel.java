package com.zhr.test;

import android.content.Context;
import android.graphics.Bitmap;

public interface DisplayPicByUrlModel {

    Bitmap getBitmapByUrl(Context context , String path , String method , int timeout);
}
