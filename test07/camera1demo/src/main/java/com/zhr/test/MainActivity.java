package com.zhr.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

public class MainActivity extends Activity {

    private final static String TAG = "MainActivity";
    private Context mContext = MainActivity.this;
    private ImageView mIv;
    private SurfaceView mSv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndPermission.with(mContext)
            .runtime()
            .permission(Permission.Group.STORAGE , Permission.Group.CAMERA)
            .onGranted(new Action<List<String>>() {
                @Override
                public void onAction(List<String> permissions) {
                    Toast.makeText(mContext , "Request Permission Successful!" , Toast.LENGTH_SHORT).show();
                }
            })
            .onDenied(new Action<List<String>>() {
                @Override
                public void onAction(@NonNull List<String> permissions) {}
            })
            .start();

        mIv = findViewById(R.id.camera_preview);

        Button captureButton = findViewById(R.id.button_capture);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG , "Take a photo!");
                CameraTakingUtils.getInstance(mIv , mSv).start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CameraTakingUtils.getInstance(mIv , mSv).releaseCarema();
    }
}
