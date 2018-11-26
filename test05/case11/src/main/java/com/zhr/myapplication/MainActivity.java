package com.zhr.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;

/**
 * 案例十一：二维码数据生成
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEt;
    private ImageView mIv;
    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void initView(){
        mEt = findViewById(R.id.et);
        mIv = findViewById(R.id.iv);
    }

    public void initData(){}

    public void click(View view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String et = mEt.getText().toString().trim();
        if (!TextUtils.isEmpty(et)) {
            OkHttpUtils.post().url(AppConstance.BASE + et)
                    .addParams("level", AppConstance.LEVEL)
                    .addParams("size", AppConstance.SIZE)
                    .build().execute(new BitmapCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Toast.makeText(mContext, "生成二维码失败！", Toast.LENGTH_LONG).show();
                    mIv.setVisibility(View.GONE);
                }

                @Override
                public void onResponse(Bitmap response, int id) {
                    if(response != null){
                        Toast.makeText(mContext, "生成二维码成功！", Toast.LENGTH_LONG).show();
                        mIv.setVisibility(View.VISIBLE);
                        mIv.setImageBitmap(response);
                    }
                }
            });
        } else {
            Toast.makeText(mContext, "未输入内容，请重新输入！", Toast.LENGTH_LONG).show();
            mIv.setVisibility(View.GONE);
        }
    }
}
