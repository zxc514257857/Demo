package com.zhr.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 案例三：文件访问权限
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click01(View view){
        // 生成私有文件，默认生成的都是私有文件
        try {
            File file = new File(mContext.getFilesDir() , "private.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("private".getBytes());
            fos.close();
            SnackBarUtils.ShortSnackbar(view , "生成私有文件成功！" , Color.WHITE, Color.RED).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click02(View view){
        // 生成可读可写文件
        try {
            // java.lang.SecurityException: MODE_WORLD_READABLE no longer supported
            // android 7.0以后 只读只写权限被废弃 使用会报错 用MODE_PRIVATE替代

            FileOutputStream fos = openFileOutput("public.txt" , Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
            fos.write("public".getBytes());
            fos.close();
            SnackBarUtils.ShortSnackbar(view , "生成公有文件成功！" , Color.WHITE, Color.RED).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click03(View view){
        // 生成只读文件
        try {
            // java.lang.SecurityException: MODE_WORLD_READABLE no longer supported
            // android 7.0以后 只读只写权限被废弃 使用会报错 用MODE_PRIVATE替代

            FileOutputStream fos = openFileOutput("readOnly.txt" , Context.MODE_WORLD_READABLE);
            fos.write("readOnly.txt".getBytes());
            fos.close();
            SnackBarUtils.ShortSnackbar(view , "生成只读文件成功！" , Color.WHITE, Color.RED).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click04(View view){
        // 生成只写文件
        try {
            // java.lang.SecurityException: MODE_WORLD_READABLE no longer supported
            // android 7.0以后 只读只写权限被废弃 使用会报错 用MODE_PRIVATE替代

            FileOutputStream fos = openFileOutput("writeOnly.txt" , Context.MODE_WORLD_WRITEABLE);
            fos.write("writeOnly.txt".getBytes());
            fos.close();
            SnackBarUtils.ShortSnackbar(view , "生成只写文件成功！" , Color.WHITE, Color.RED).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
