package com.zhr.test.ui.sec.view;

import android.widget.ImageView;
import android.widget.TextView;

public interface MvcView {

    void showTextView(TextView tv, int resid);

    void showImageView(ImageView iv , int resid);
}
