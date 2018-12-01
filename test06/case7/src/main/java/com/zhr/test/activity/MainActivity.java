package com.zhr.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zhr.test.R;

/**
 * 案例七：短信助手
 * 练习startActivityForResult
 * 练习RecyclerView中 LayoutManager的创建 ItemDecoration的创建 Adapter的OnItemClickListener的创建以及Adapter中的Item点击事件和长按事件的自定义
 * 以及Item的滑动删除和长按拖拽
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PHONE = 1;
    private static final int REQUEST_CODE_SMS = 2;
    private static final int RESULT_CODE_SMS = 3;
    private static final int RESULT_CODE_PHONE = 4;
    private EditText mEtPhone;
    private EditText mEtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtPhone = findViewById(R.id.et_phone);
        mEtMessage = findViewById(R.id.et_message);
    }

    /**
     * 选择号码
     * @param view
     */
    public void selectContact(View view){
        Intent intent = new Intent();
        intent.setClass(this , PhoneActivity.class);
//        startActivity(intent);
        // 意思是进去之后再返回，要从进去的地方取值
        startActivityForResult(intent , REQUEST_CODE_PHONE);
    }

    /**
     * 选择短信
     * @param view
     */
    public void selectSmsContent(View view){
        Intent intent = new Intent();
        intent.setClass(this , SmsActivity.class);
//        startActivity(intent);
        // 意思是进去之后再返回，要从进去的地方取值
        startActivityForResult(intent , REQUEST_CODE_SMS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_PHONE){
            Log.i(TAG, "onActivityResult: REQUEST_CODE_PHONE");
            if(resultCode == RESULT_CODE_PHONE){
                String phoneData = data.getStringExtra("phoneData");
                mEtPhone.setText(phoneData);
            }
        }else if(requestCode == REQUEST_CODE_SMS){
            Log.i(TAG, "onActivityResult: REQUEST_CODE_SMS");
            if(resultCode == RESULT_CODE_SMS){
                String smsData = data.getStringExtra("smsData");
                mEtMessage.setText(smsData);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
