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
 * 案例一：学生信息管理系统之学生信息保存 xml文件的数据持久化存储
 * 存储成功后，如何在/data/data/包名/cache/文件夹下找到该xml文件？？？
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
