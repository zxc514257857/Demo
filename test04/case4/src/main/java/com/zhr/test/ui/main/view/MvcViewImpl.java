package com.zhr.test.ui.main.view;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class MvcViewImpl implements MvcView {

    private static final String TAG = MvcViewImpl.class.getSimpleName();

    @Override
    public void showImageView(ImageView iv , int resid) {
        iv.setImageResource(resid);
    }

    @Override
    public void startAnim(ImageView iv) {
        // 创建并开启帧动画
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void stopAnim(ImageView iv) {
        // 创建并开启帧动画
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.stop();
    }
}
