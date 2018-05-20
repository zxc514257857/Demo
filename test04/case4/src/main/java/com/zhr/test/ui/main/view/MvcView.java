package com.zhr.test.ui.main.view;

import android.widget.ImageView;

public interface MvcView {

    void showImageView(ImageView iv , int resid);

    void startAnim(ImageView iv);

    void stopAnim(ImageView iv);
}
