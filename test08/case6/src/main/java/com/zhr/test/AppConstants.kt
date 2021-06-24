package com.zhr.test

// 以object关键字修饰的类，其成员变量都是静态的，其里面的方法都是单例模式的
object AppConstants {

    const val DEBUG: Boolean = true
    const val APP_MAIN: String = "/app/Main"
    const val APP_SEC: String = "/app/Sec"
    const val APP_JSON_SERVICE: String = "/app/json"
}