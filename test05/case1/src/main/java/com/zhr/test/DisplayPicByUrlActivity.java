package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 案例一：网络图片查看器
 * 访问网络，进程切换练习
 */
public class DisplayPicByUrlActivity extends AppCompatActivity implements DisplayPicByUrlView, View.OnClickListener {

    private static final String TAG = DisplayPicByUrlActivity.class.getSimpleName();
    private Context mContext = DisplayPicByUrlActivity.this;
    private EditText mEt;
    private ImageView mIv;
    private Button mBtn;
    private DisplayPicByUrlModel mModel;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case AppConstance.GET_PIC_SUCCESS:
                    final Bitmap bitmap = (Bitmap) msg.obj;
                    // 更新UI，需在主线程中执行
                    ThreadUtil.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            setImageBitmap(mIv , bitmap);
                        }
                    });

                case AppConstance.GET_PIC_FAIL:
                    showToast(mContext , "获取图片资源失败！" , Toast.LENGTH_LONG);
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mEt = findViewById(R.id.et);
        mBtn = findViewById(R.id.btn);
        mIv = findViewById(R.id.iv);
    }

    private void initData() {
        mModel = new DisplayPicByUrlModelImpl();
        clickButton(mBtn);
        setMaxTwoLines(mEt);
    }

    /**
     * 点击按钮
     * @param btn
     */
    @Override
    public void clickButton(Button btn) {
        btn.setOnClickListener(this);
    }

    @Override
    public boolean checkEtEmpty(EditText et) {
        return et.getText().toString().trim().isEmpty();
    }

    @Override
    public void showToast(Context context , CharSequence text , int duration) {
        Toast.makeText(context , text , duration).show();
    }

    @Override
    public void setImageBitmap(ImageView iv, Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

    @Override
    public void setMaxTwoLines(final EditText et) {
        et.addTextChangedListener(new TextWatcher() {
            String currentText = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {
                int currentLines = et.getLineCount();
                int maxLines = et.getMaxLines();
                Log.i(TAG , "currentLines: " + currentLines);
                Log.i(TAG , "maxLines: " + maxLines);
                // 限制最大输入行数
                if (currentLines <= et.getMaxLines()) {
                    currentText = s != null ? s.toString() : "";
                }else {
                    et.setText(currentText);
                    et.setSelection(currentText.length());
                }
                Log.i(TAG, "currentText: " + currentText);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:
                if(checkEtEmpty(mEt)){
                    showToast(this , "图片路径输入为空，请重新输入！" , Toast.LENGTH_LONG);
                }else{
                    // 网络请求是耗时性操作，需要在子线程中进行
                    ThreadUtil.runOnThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final Bitmap bitmap = mModel.getBitmapByUrl(mContext , mEt.getText().toString().trim(), "GET", AppConstance.INTERNET_TIMEOUT);
                                Message msg = Message.obtain();
                                msg.what = AppConstance.GET_PIC_SUCCESS;
                                msg.obj = bitmap;
                                mHandler.handleMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Message msg = Message.obtain();
                                msg.what = AppConstance.GET_PIC_FAIL;
                                mHandler.handleMessage(msg);
                            }
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
}
