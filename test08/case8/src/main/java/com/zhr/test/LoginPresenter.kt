package com.zhr.test

class LoginPresenter {

    private val loginModel by lazy {
        LoginModel()
    }

    fun checkUserState(username: String, password: String, callback: checkUserState) {

    }

    interface checkUserState {
        fun onUserAva()
        fun onUserNotAva()
    }
}