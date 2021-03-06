package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 案例一：学生信息管理系统之学生信息保存 xml文件的数据持久化存储(序列化)
 *
 * 内存卡中保存的内容在/storage/emulated/legacy文件夹或/storage/sdcard0或/mnt/sdcard下找，
 * 这三个基本都是对应在/mnt/shell/emulated/0文件夹下
 *
 * SharedPreference内容保存在/data/data/包名/shared_prefs文件夹下
 * Gilde图片缓存位置/data/data/包名/cache/image_manager_disk_cache文件夹下
 *
 * mContext.getCacheDir()是指 /data/data/包名/cache文件夹
 * mContext.getFilesDir()是指 /data/data/包名/files文件夹
 * mContext.getExternalCacheDir()是指 /mnt/sdcard/Android/data/包名/cache文件夹
 * mContext.getExternalFilesDir()是指 /mnt/sdcard/Android/data/包名/files文件夹
 * mContext.getExternalFilesDir("test")是指 /mnt/sdcard/Android/data/包名/files/test文件夹
 * mContext.getPackageCodePath()是指 /data/app/包名-1.apk文件夹
 * mContext.getPackageResourcePath()是指 /data/app/包名-1.apk文件夹
 * mContext.getDatabasePath("test")是指 /data/data/包名/databases/test文件夹
 * mContext.getDir("test" , Context.MODE_PRIVATE)是指 /data/data/包名/app_test文件夹
 *
 * Environment.getDataDirectory() 是指 /data文件夹
 * Environment.getDownloadCacheDirectory() 是指 /cache文件夹
 * Envrionment.getExternalStorageDirectory() 是指/mnt/sdcard文件夹
 * Environment.getRootDirectory 是指/system文件夹
 * Environment.getExternalStoryPublicDirectory("test") 是指/mnt/sdcard/test文件夹
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_number)
    EditText mEtNumber;
    @Bind(R.id.rb_male)
    RadioButton mRbMale;
    @Bind(R.id.rb_female)
    RadioButton mRbFemale;
    @Bind(R.id.rg_sex)
    RadioGroup mRgSex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 点击保存按钮时进行判断数据是否保存成功并提示
     * @param view 按钮
     */
    public void save(View view){

        String name = mEtName.getText().toString().trim();
        String number = mEtNumber.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(number)){
            Toast.makeText(mContext, "学生姓名或学号为空，请输入！", Toast.LENGTH_SHORT).show();
            return;
        }

        // StringBuilder是线程不安全的，相对于StringBuffer效率高
        StringBuilder builder = new StringBuilder();
        // 把选择的RadioButton转化为id进行保存
        int id = mRgSex.getCheckedRadioButtonId();
        String sex = "male";
        if(id == R.id.rb_male){
            sex = "male";
        }else if(id == R.id.rb_female){
            sex = "female";
        }

        //  <?xml version="1.0" encoding="utf-8"?>
        //  <student>
        //      <name>张三</name>
        //      <number>110001</number>
        //      <sex>male</sex>
        //  </student>

        // 序列化xml文件
        builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        builder.append("<student>");
        builder.append("<name>");
        builder.append(name);
        builder.append("</name>");
        builder.append("<number>");
        builder.append(number);
        builder.append("</number>");
        builder.append("<sex>");
        builder.append(sex);
        builder.append("</sex>");
        builder.append("</student>");

        try {
            File file = new File(mContext.getCacheDir() , name + ".xml");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(builder.toString().getBytes());
            fos.close();
            Toast.makeText(mContext, "数据保存成功！", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(mContext, "数据保存失败！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
