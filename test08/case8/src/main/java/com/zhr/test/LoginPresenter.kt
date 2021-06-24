package com.zhr.test

import android.text.TextUtils
import com.zhr.test.LoginModel.Companion.STATE_LOGIN_FAILED
import com.zhr.test.LoginModel.Companion.STATE_LOGIN_LOADING
import com.zhr.test.LoginModel.Companion.STATE_LOGIN_SUCCESS
import java.util.*


class LoginPresenter {

    val random: Random = Random()

    private val loginModel by lazy {
        LoginModel()
    }

    fun checkUserState(username: String, password: String, callback: CheckUserStateCallback) {
        if (TextUtils.isEmpty(username)) {
            callback.onUsernameEmpty()
            return
        }
        if (TextUtils.isEmpty(password)) {
            callback.onPasswordEmpty()
            return
        }

        // 0表示该用户名不可用  1表示该用户名可用
        loginModel.checkUserState(username) {
            when (it) {
                0 -> {
                    callback.onUserCannotUse()
                }
                1 -> {
                    callback.onUserCanUse()
                }
                else -> {
                    callback.onUserCannotUse()
                }
            }
        }
    }

    fun doLogin(username: String, password: String, callback: LoginStateChangeCallabck) {
        loginModel.doLogin(username, password) {
            when (it) {
                STATE_LOGIN_LOADING -> {
                    callback.onLoading()
                }
                STATE_LOGIN_SUCCESS -> {
                    callback.onLoginSuccess()
                }
                STATE_LOGIN_FAILED -> {
                    callback.onLoginFailed()
                }
            }
        }
    }

    interface CheckUserStateCallback {
        fun onUserCanUse()
        fun onUserCannotUse()
        fun onUsernameEmpty()
        fun onPasswordEmpty()
    }

    interface LoginStateChangeCallabck {
        fun onLoading()
        fun onLoginSuccess()
        fun onLoginFailed()
    }
}