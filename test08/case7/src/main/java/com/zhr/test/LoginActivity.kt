package com.zhr.test

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 案例七：MVC示例代码、Kotlin语法的使用：by lazy、接口回调的写法
 */
class LoginActivity : AppCompatActivity(), LoginModel.LoginStateChangeCallabck {

    /**
     * by lazy
     * 是通过属性代理模式来实现的懒加载，在第一次使用的时候才会执行，并且只会执行一次
     * 默认是线程安全的，内部通过双重判断加锁来保证只执行一次代码块赋值
     */
    private val loginModel by lazy {
        LoginModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            // 去执行登录
            toLogin()
        }
    }

    private fun toLogin() {
        // 做登录的逻辑处理
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()
        if (TextUtils.isEmpty(username)) {
            // 检查帐号格式是否正确
            Toast.makeText(this, "帐号有误，请重新输入！", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            // 检查密码格式是否正确
            Toast.makeText(this, "密码有误，请重新输入！", Toast.LENGTH_LONG).show()
            return
        }
        // 给密码加盐 xxx
        // 检查用户名状态
        loginModel.checkUserState(username) {
            when (it) {
                0 -> {
                    // 该用户名不可用
                    btnLogin.text = "用户名不可用"
                    btnLogin.isEnabled = false
                }
                1 -> {
                    // 该用户名可用
                    // 进行登录，此操作为异步
                    loginModel.doLogin(username, password, this)
                    // 点击完后禁止点击，防止数据重复提交
                    btnLogin.isEnabled = false
                }
                else -> {
                    // 该用户名不可用
                    btnLogin.text = "用户名不可用"
                    btnLogin.isEnabled = false
                }
            }
        }
    }

    override fun onLoading() {
        btnLogin.text = "登录中"
    }

    override fun onLoginSuccess() {
        btnLogin.text = "登录成功"
    }

    override fun onLoginFailed() {
        btnLogin.text = "登录失败"
    }
}