package com.zhr.test.bean

import java.io.Serializable

class Person3: Serializable{

    var name: String? = null
    var age: Int? = null

    constructor(name: String?, age: Int?) {
        this.name = name
        this.age = age
    }
}