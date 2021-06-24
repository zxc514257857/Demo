package com.zhr.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * MVP示例代码
 */
class LoginActivity : AppCompatActivity(), LoginPresenter.LoginStateChangeCallabck {

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
    }

    private fun toLogin() {
        // sout 是System.out.println的简写
        println()
        // test.sout 是println(test)的简写
//        println(test)

        // 如果要学习打断点的使用，在 https://www.bilibili.com/video/BV1Dk4y1C7mm?p=5 这里面的第19分钟有用到
        // 最主要的就是 Attach到当前进程、以及Step into和Resume Progremzhe三个按钮

        val username: String = etUsername.text.toString().trim()
        val password: String = etPassword.text.toString().trim()
        // 检查用户名状态
        // 这里通过匿名内部类实现或者通过this 外部类去实现都是可以的
        loginPresenter.checkUserState(username, password, object : LoginPresenter.CheckUserStateCallback {

            // View层不用处理状态，直接显示对应状态的数据就可以了
            override fun onUsernameEmpty() {
                Toast.makeText(this@LoginActivity, "帐号为空，请重新输入！", Toast.LENGTH_LONG).show()
            }

            override fun onPasswordEmpty() {
                Toast.makeText(this@LoginActivity, "密码为空，请重新输入！", Toast.LENGTH_SHORT).show()
            }

            override fun onUserCannotUse() {
                // 该用户名不可用
                btnLogin.text = "用户名不可用"
                btnLogin.isEnabled = false
            }

            override fun onUserCanUse() {
                // 该用户名可用
                loginPresenter.doLogin(username, password, this@LoginActivity)
                btnLogin.isEnabled = false
            }
        })
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