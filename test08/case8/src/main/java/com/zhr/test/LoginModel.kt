package com.zhr.test

import java.util.*

class LoginModel {

    val random: Random = Random()

    companion object {
        const val STATE_LOGIN_LOADING: Int = 0
        const val STATE_LOGIN_SUCCESS: Int = 1
        const val STATE_LOGIN_FAILED: Int = 2
    }

    fun checkUserState(username: String, block: (Int) -> Unit) {
        val value: Int = random.nextInt(2)
        block.invoke(value)
    }

    fun doLogin(username: String, password: String, block: (Int) -> Unit) {
        block.invoke(STATE_LOGIN_LOADING)
        when (random.nextInt(2)) {
            0 -> {
                block.invoke(STATE_LOGIN_SUCCESS)
            }
            1 -> {
                block.invoke(STATE_LOGIN_FAILED)
            }
            else -> {
                block.invoke(STATE_LOGIN_LOADING)
            }
        }
    }
}