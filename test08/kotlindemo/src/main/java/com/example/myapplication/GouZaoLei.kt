package com.example.myapplication

/**
 * 类通过构造函数传入参数(主构造函数)
 */
class GouZaoLei constructor(name : String){

    var url : String = "123"

    // 次构造函数
//    constructor(name1 : String)
}

class GZ private constructor(){

}

/**
 * 抽象类中的抽象方法
 */
abstract class Derived : Base(){
    override abstract fun test()
}

/**
 * 类和方法默认是final的，不能被继承。声明open的，允许被继承
 */
open class Base{
    open fun test(){

    }
}

/**
 * 嵌套类
 */
open class Outer {
    class Nested {
        fun aaa(){}
    }

    private val bar : Int = 1
    var v = "成员属性"
    /**
     * 嵌套内部类
     */
    inner class Inner{
        fun foo() = bar
        var o = v
        fun innerTest(){
            var o = this@Outer
        }
    }
}

class aaa : Outer(){

}

/**
 * 匿名内部类
 */
class Test{
    var v = "成员属性"
    fun setInterface(test : TestInterface){
        test.aaa()
    }
}

interface TestInterface{
    fun aaa()
}

open class A {
    private var count : Int = 5
}

class B : A(){
    val count : Int = 3
}

