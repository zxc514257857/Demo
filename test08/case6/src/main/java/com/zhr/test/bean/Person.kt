package com.zhr.test.bean

class Person {

    var name: String? = null
    var age: Int? = null

    constructor(name: String?, age: Int?) {
        this.name = name
        this.age = age
    }

    override fun toString(): String {
        return "Person(name=$name, age=$age)"
    }
}