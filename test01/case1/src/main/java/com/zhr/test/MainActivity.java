package com.zhr.test;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

/**
 * 案例一：一键呼叫大儿子
 */
public class MainActivity extends AppCompatActivity{

    private Context mContext = MainActivity.this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    .permission(Manifest.permission.CALL_PHONE)
                    .rationale(rationaleListener)
                    .start();
        }

    }

    public void call(View view){
        /**
         * 打电话
         */
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://15527962751"));
        startActivity(intent);

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
}
