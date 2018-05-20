package com.zhr.test.ui.main.controler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zhr.test.R;
import com.zhr.test.ui.main.view.MvcView;
import com.zhr.test.ui.main.view.MvcViewImpl;

/**
 * 案例四：帧动画
 * 帧动画设置在drawable目录下，以animation-list为节点，根节点下的oneshot 设置为true表示不循环，为false表示循环播放
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private MvcView mMv;
    private ImageView mIv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv = (ImageView) findViewById(R.id.iv);
        mMv = new MvcViewImpl();
    }

    public void click01(View view){
        mMv.showImageView(mIv , R.drawable.in_order_anim);
        mMv.startAnim(mIv);
    }

    public void click02(View view){
        mMv.stopAnim(mIv);
    }

    public void click03(View view){
        mMv.showImageView(mIv , R.drawable.inverted_order_anim);
        mMv.startAnim(mIv);
    }
}
