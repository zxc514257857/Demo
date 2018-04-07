package com.zhr.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 案例四：QQ登录SP缓存帐号密码
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

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
        initData();
    }

    private void initData() {

        mBtLogin.setOnClickListener(this);

        boolean checked = PrefUtils.getBoolean(mContext, "isChecked", false);
        if(checked){
            mCbRememberPwd.setChecked(checked);

            String userName = PrefUtils.getString(mContext, "userName", "");
            String password = PrefUtils.getString(mContext, "password", "");
            mEtUserName.setText(userName);
            mEtPassword.setText(password);
            mEtUserName.setSelection(userName.length());
            mEtPassword.setSelection(password.length());
        }else{
            Log.i(TAG, "没有记住密码，不需要回显！");
        }
    }

    @Override
    public void onClick(View view) {
        String userName = mEtUserName.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
            SnackBarUtils.ShortSnackbar(view , "帐号或者密码为空！" , Color.WHITE , Color.RED).show();
        }else{
            PrefUtils.putBoolean(mContext , "isChecked" , mCbRememberPwd.isChecked());
            if(mCbRememberPwd.isChecked()){
                PrefUtils.putString(mContext , "userName" , userName);
                PrefUtils.putString(mContext , "password" , password);
                SnackBarUtils.ShortSnackbar(view, "帐号密码保存成功！", Color.WHITE, Color.RED).show();
            }else{
                Log.i(TAG, "不需要记住密码！");
            }
        }
    }
}
