package com.zhr.test

import android.util.Log
import java.util.*


class LoginModel {

    private val TAG: String = "LoginModel"
    private val random = Random()

    // TODO: 2021/6/13/013 这里接口回调的方式好好学习
    fun doLogin(username: String, password: String, callback: LoginStateChangeCallabck) {
        callback.onLoading()
        // 开始去调用登录的API
        // 有结果，此操作为耗时操作
        val value: Int = random.nextInt(2)
        if (0 == value) {
            callback.onLoginSuccess()
        } else {
            callback.onLoginFailed()
        }
        Log.e(TAG, "value:::$value")
    }

    /**
     * block方法的使用，根据返回的值的内容来确定 block值的数据类型。接收要用when关键字进行接收
     */
    fun checkUserState(username: String, block: (Int) -> Unit) {
        // 0表示该用户名不可用  1表示该用户名可用
        val value: Int = random.nextInt(2)
        block.invoke(value)
        Log.e(TAG, "value:::$value")
    }

    // TODO: 2021/6/13/013 这里接口回调的方式好好学习
    interface LoginStateChangeCallabck {
        fun onLoading()
        fun onLoginSuccess()
        fun onLoginFailed()
    }
}