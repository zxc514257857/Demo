package com.zhr.test

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Kotlin中分辨实现和继承的区别的话
 * 就是判断后面那个的类型，是类还是接口，是类就是继承、是接口就是实现
 */
@Route(path = AppConstants.APP_JSON_SERVICE)
class JsonSerializationService : SerializationService {

    var gson: Gson? = null

    override fun init(p0: Context?) {
        gson = Gson()
    }

    override fun <T : Any?> json2Object(input: String?, clazz: Class<T>?): T? {
        checkJson()
        return gson?.fromJson(input, clazz)
    }

    override fun object2Json(instance: Any?): String? {
        checkJson()
        return gson?.toJson(instance)
    }

    override fun <T : Any?> parseObject(input: String?, clazz: Type?): T? {
        checkJson()
        return gson?.fromJson(input, clazz)
    }

    private fun checkJson() {
        if (null == gson) {
            gson = Gson()
        }
    }
}