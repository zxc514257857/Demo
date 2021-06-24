package com.zhr.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_login.*

/**
 * MVP示例代码
 */
class LoginActivity : AppCompatActivity() {

    private val TAG: String = "LoginActivity"

    private val loginPresenter by lazy {
        LoginPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            toLogin()
        }
        // 在EditText中监听内容变化，判断帐号是否可用
        etUsername.addTextChangedListener(object : TextWatcher {
            // EditText改变前调用
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.e(TAG, "beforeTextChanged: ")
            }

            // EditText改变中调用
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.e(TAG, "onTextChanged: ", )
            }

            // EditText改变后调用
            override fun afterTextChanged(s: Editable?) {
                Log.e(TAG, "afterTextChanged: ", )
            }
        })
    }

    private fun toLogin() {
        val username: String = etUsername.text.toString().trim()
        val password: String = etPassword.text.toString().trim()
//        loginPresenter.checkUserState(username)


        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "帐号有误，请重新输入！", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码有误，请重新输入！", Toast.LENGTH_SHORT).show()
            return
        }
    }
}