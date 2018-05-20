package com.zhr.test.ui.sec.view;

import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

public class MvcViewImpl implements MvcView {

    private static final String TAG = MvcViewImpl.class.getSimpleName();

    @Override
    public void showTextView(TextView tv , int resid) {
        if(tv != null){
            tv.setText(resid);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP , 26);
            tv.setGravity(Gravity.CENTER);
        }
    }

    @Override
    public void showImageView(ImageView iv , int resid) {
        if(iv != null){
            iv.setImageResource(resid);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
}
