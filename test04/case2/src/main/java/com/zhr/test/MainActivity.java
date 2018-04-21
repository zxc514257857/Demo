package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 案例二：学生信息管理系统配合ListView的使用
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private MvcView mMv;

    @Bind(R.id.lv)
    ListView mLv;
    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.rb_male)
    RadioButton mRbMale;
    @Bind(R.id.rb_female)
    RadioButton mRbFemale;
    @Bind(R.id.rg_sex)
    RadioGroup mRgSex;
    private StudentDao mDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mMv = new MvcViewImpl();
        mDao = new StudentDao(mContext);
        showSqlData(mDao);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mMv.showToast(mContext, "第" + i + "个条目被点击了！");
    }

    public void save(View view) {
        String name = mEtName.getText().toString().trim();
        String sex = "male";
        if(TextUtils.isEmpty(name)){
            Toast.makeText(mContext, "请输入学生姓名！", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = mRgSex.getCheckedRadioButtonId();
        if(id == R.id.rb_male){
            sex = "male";
        }else if(id == R.id.rb_female){
            sex = "female";
        }

        mDao.add(name , sex);
        mMv.showToast(mContext , "学生信息添加成功！");
        showSqlData(mDao);
    }

    /**
     * 展示数据库数据
     */
    private void showSqlData(StudentDao dao) {
        List<Student> students = dao.findAll();
        mLv.setAdapter(new MyListViewAdapter(mMv, mContext , students));
        mLv.setOnItemClickListener(this);
    }
}
