package com.zhr.test;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 案例二：QQ登录外置存储卡缓存帐号密码
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    /**
     * ButterKnife 8.6.0有问题，容易爆空指针 使用7.0.1较好
     */
    @Bind(R.id.et_userName)
    EditText mEtUserName;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.cb_rememberPwd)
    CheckBox mCbRememberPwd;
    @Bind(R.id.bt_login)
    Button mBtLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /**
         * 添加了权限申请仍然报错的原因是：android 6.0使用危险权限时需要动态申请
         * 使用 AndPermission库 在Splash界面过后就进行权限申请
         */
        if(AndPermission.hasPermission(this, Manifest.permission.CALL_PHONE)) {
            // 有权限

        } else {
            // 申请权限
            AndPermission.with(mContext)
                    .requestCode(200)
                    .permission(Permission.STORAGE)
                    .rationale(rationaleListener)
                    .start();
        }

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

    private void initData() {
        mBtLogin.setOnClickListener(new MyClickListener());

        // 判断是否记住密码，记住了需要回显
        boolean checked = PrefUtils.getBoolean(mContext, "checked", false);

        if (checked) {
            // 保持记住密码为勾选状态
            mCbRememberPwd.setChecked(checked);

            File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
            if (file.exists() && file.length() > 0) {
                try {
                    // 读流操作
                    FileInputStream fis = new FileInputStream(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String info = br.readLine();

                    String[] split = info.split("###");
                    String userName = split[0];
                    String password = split[1];
                    mEtUserName.setText(userName);
                    mEtPassword.setText(password);
                    mEtUserName.setSelection(userName.length());
                    mEtPassword.setSelection(password.length());

                    fis.close();
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            mCbRememberPwd.setChecked(checked);
            Log.i(TAG, "没有记住密码，不需要回显！");
        }
    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String userName = mEtUserName.getText().toString().trim();
            String password = mEtPassword.getText().toString().trim();
            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                SnackBarUtils.ShortSnackbar(view, "帐号或密码为空！", Color.WHITE, Color.RED).show();
            } else {
                boolean checked = mCbRememberPwd.isChecked();
                PrefUtils.putBoolean(mContext, "checked", checked);
                // 勾选记住密码
                if (checked) {
                    // 开始文件存储
                    try {
                        // 如果没有内存卡
                        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                            SnackBarUtils.ShortSnackbar(view, "没有内存卡可供缓存！", Color.WHITE, Color.RED).show();
                            return;
                        }

                        long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
                        if(freeSpace > 0){
                            String size = Formatter.formatFileSize(mContext, freeSpace);
                            Log.i(TAG , "size：" + size);
                            File file = new File(Environment.getExternalStorageDirectory() , "info.txt");
                            FileOutputStream fos = new FileOutputStream(file);
                            fos.write((userName + "###" + password).getBytes());
                            fos.close();
                            SnackBarUtils.ShortSnackbar(view, "帐号密码保存成功！", Color.WHITE, Color.RED).show();
                        }else{
                            SnackBarUtils.ShortSnackbar(view, "帐号密码保存成功！", Color.WHITE, Color.RED).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        SnackBarUtils.ShortSnackbar(view, "内存卡容量不够！", Color.WHITE, Color.RED).show();
                    }
                } else {
                    Log.i(TAG, "不需要记住密码！");
                }
            }
        }
    }
}
