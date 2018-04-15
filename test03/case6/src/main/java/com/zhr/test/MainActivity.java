package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 案例六：数据库Dao类代码的运行验证
 * 创建的数据库db文件在 data/data/包名/databases/student.db
 * xml文件的解析方式有三种：sax解析，dom解析和pull解析
 * Sqlite3是安卓中内置的数据库操作工具
 * 1,adb shell    2,cd data/data/包名/databases    3,sqlite3 student.db    4,输入数据库操作命令
 */
public class MainActivity extends AppCompatActivity implements MvcView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_phoneNum)
    EditText mEtPhoneNum;
    @Bind(R.id.rb_male)
    RadioButton mRbMale;
    @Bind(R.id.rb_female)
    RadioButton mRbFemale;
    @Bind(R.id.rg_sex)
    RadioGroup mRgSex;
    @Bind(R.id.ll_result)
    LinearLayout mLlResult;
    private StudentDao mDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDao = new StudentDao(mContext);
        refreshData();
    }

    public void save(View view) {
        String name = mEtName.getText().toString().trim();
        String phoneNum = mEtPhoneNum.getText().toString().trim();
        String sex = "";
        if (TextUtils.isEmpty(name)) {
            showToast(mContext, "请输入学生姓名！");
            return;
        }
        if(TextUtils.isEmpty(phoneNum)){
            showToast(mContext , "请输入学生联系方式！");
            return;
        }

        int id = mRgSex.getCheckedRadioButtonId();
        if (id == R.id.rb_male) {
            sex = "male";
        } else if (id == R.id.rb_female) {
            sex = "female";
        }
        Log.i(TAG, "name:" + name + ",sex:" + sex + ",phoneNum:" + phoneNum);
        mDao.add(name , sex , phoneNum);
        showToast(mContext , "数据添加成功！");
        refreshData();
    }

    private void refreshData() {
        List<StudentBean> students = mDao.findAll();
        Log.i(TAG, "students:" + students.toString());
        removeView(mLlResult);
        for (StudentBean student : students) {
            TextView tv = new TextView(mContext);
            tv.setText(student.toString());
            addView(mLlResult, tv);
        }
    }

    @Override
    public void removeView(LinearLayout ll) {
        if (ll != null) {
            ll.removeAllViews();
        }
    }

    @Override
    public void addView(LinearLayout ll, TextView tv) {
        if (ll != null && tv != null) {
            ll.addView(tv);
        }
    }

    @Override
    public void showTextView(TextView tv, String content) {
        if (tv != null && content != null) {
            tv.setText(content);
        }
    }

    @Override
    public void showToast(Context mContext, String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_LONG).show();
    }
}
