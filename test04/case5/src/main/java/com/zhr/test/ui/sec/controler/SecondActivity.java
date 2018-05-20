package com.zhr.test.ui.sec.controler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhr.test.R;
import com.zhr.test.ui.sec.view.MvcView;
import com.zhr.test.ui.sec.view.MvcViewImpl;

public class SecondActivity extends AppCompatActivity {

    private Context mContext = SecondActivity.this;
    private TextView mTv;
    private MvcView mMvcView;
    private ImageView mIv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        mMvcView = new MvcViewImpl();
        mTv = (TextView) findViewById(R.id.tv);
        mIv = (ImageView) findViewById(R.id.iv);
        mMvcView.showTextView(mTv , R.string.AppName);
        mMvcView.showImageView(mIv , R.drawable.rating);
    }
}
