package com.example.myapplication

/**
 * 一般类
 */
class Greeter {

    /**
     * 一般方法
     */
    fun test1(string : String){
        println("Hello $string")
    }

    /**
     * 有返回值类型的方法
     */
    fun test2(int : Int) : Boolean{
        return 1 == int
    }

    /**
     * 有访问控制符的方法
     */
    public fun test3(int : Int) : Boolean{
        return 2 == int
    }

    /**
     * 有访问控制符的方法
     */
    private fun test4(int : Int) : Boolean{
        return 3 == int
    }

    /**
     * 可变长参数以及for循环
     */
    fun test5(vararg v : Int){
        for(vt in v){
            println(vt)
        }
    }

    /**
     * lambda表达式使用
     */
    fun test6(){
        var lambda : (Int , Int) -> Int = {
            x , y -> x + y
        }
        println(lambda(1 , 2))
    }
}
