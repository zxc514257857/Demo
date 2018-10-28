package com.zhr.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public interface DisplayPicByUrlView {

    void clickButton(Button btn);

    boolean checkEtEmpty(EditText et);

    void showToast(Context context , CharSequence text , int duration);

    void setImageBitmap(ImageView iv , Bitmap bitmap);

    void setMaxTwoLines(EditText et);
}
