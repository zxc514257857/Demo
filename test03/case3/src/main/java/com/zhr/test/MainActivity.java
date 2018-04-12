package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhr.test.R.id.rb_male;


/**
 * 案例三：学生信息管理系统之学生信息保存 使用XmlSerializer序列化器进行xml文件的序列化
 * MVP学习地址：https://blog.csdn.net/dantestones/article/details/50899235
 * https://blog.csdn.net/dantestones/article/details/51445208
 * http://www.jcodecraeer.com/a/anzhuokaifa/2017/1020/8625.html?1508484926
 */
public class MainActivity extends AppCompatActivity implements MvcView{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_number)
    EditText mEtNumber;
    @Bind(rb_male)
    RadioButton mRbMale;
    @Bind(R.id.rb_female)
    RadioButton mRbFemale;
    @Bind(R.id.rg_sex)
    RadioGroup mRgSex;
    @Bind(R.id.tv_result)
    TextView mTvResult;
    private MvcModelImpl mMvcModelImpl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMvcModelImpl = new MvcModelImpl();
    }

    public void save(View view){
        String name = mEtName.getText().toString().trim();
        String number = mEtNumber.getText().toString().trim();
        String sex = "male";
        int id = mRgSex.getCheckedRadioButtonId();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(number)){
            showToast(mContext , "学生姓名或学号为空，请重新输入！");
            return;
        }

        if( id == R.id.rb_male){
            sex = "male";
        }else if(id == R.id.rb_female){
            sex = "female";
        }

        mMvcModelImpl.saveStuInfo(mContext , name , number , sex);
    }

    public void read(View view){
        String name = mEtName.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            showToast(mContext , "对不起，请输入您要查询的学生姓名！");
            return;
        }

        mMvcModelImpl.readStuInfo(mContext , name , mTvResult);
    }

    @Override
    public void showToast(Context context , String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTextView(TextView textView , String string) {
        if(textView != null && string != null){
            textView.setText(string);
        }
    }
}
