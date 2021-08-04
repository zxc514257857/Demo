package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Greeter().test1("World!")
        var test2 = Greeter().test2(1)
        println("test2：$test2")
        val test3 = Greeter().test3(2)
        println("test3：$test3" )
        Greeter().test5(1 , 2 , 3)
        Greeter().test6()

        // var <标识符> ： <数据类型> = <初始化值>
        // 可变变量定义
        var a : Int = 5
        // 定义变量的时候 如果不在声明时初始化 则必须提供变量类型
        var b = 8
        // 不可变变量定义
        val c : Boolean = true
        var d : Int = 1
        d += 1
        val e : Int = 1
//        e += 1
        println(d)

        /*
        多行注释
         */
        // 单行注释

        // 字符串模版  之变量名模版
        println("hahaha:$test3")
        // 字符串模版  之表达式模版
        println("123::${Greeter().test3(2)}")

        // Kotlin null检查机制
        // 如果变量后面不写？表明可以接受为null的变量  即为可空类型变量，还有非空类型变量
        println("111:::${test(null)}")
        // 要安全调用可空类型，则可以 ?. 进行调用
        println(test("aaa")?.length)
        // 等同于这样写
        val test = test(null)
        println({if(null != test){test.length}})
        // 非空断言 !!
        println(test("bbb")!!.length)

        // 可空变量使用三种方式   1、if else判断   2、?. 可空安全调用    3、!! 非空断言
        // ?.和 !! 可以替换，但！！使用为null的时候则出现了NPE

        // ?. 如果变量为null 则表达式为null 如果变量不为null 则就是原表达式
        // ?: 如果变量为null时 取后面的那个值  如果不为null就是变量的值
        // as? 为null或者原表达式的值
        // !!  为变量的值或者NPE
        // ?   变量加？表示Nullable  变量不加？表示NotNull
        println(test?:"xxx")

        // 数据类型检测和自动类型转换  is 和instanceof关键字类似功能
        println("test1:bb:${test1("str")}")

        // 数组 集合和区间
        // 集合分为只读集合 和可变集合  listOf和  mutableListOf
        // 只读集合不支持添加删除操作
        val listOf = listOf(1, 2)
        println("listof:${listOf}")
        // 可变集合支持 添加删除操作
        var mutableListOf = mutableListOf("11", "22", null)
        mutableListOf.add("33")
        mutableListOf.remove("11")
        // 过滤集合中为null的数据
        println("mutableListOf:${mutableListOf.filterNotNull()}")
        // to 中缀调用函数名
        var mapOf = mapOf("a" to 1, "b" to 2)
        println("mapOf$mapOf")
        // 集合的继承关系 Collection - MutableCollection - MutableListOf
        // 打印集合直接打印即可 打印数组需要joinToString打印
        // 创建String类型的数组用arrayOf，创建Int类型的数组用intArrayOf
        arrayOf("" , 2)
        intArrayOf(1 , 2)
        val intArray = IntArray(4)
        intArray.set(1 , 100)
        var toList = intArray.toList()
        // 数组joinToString打印出数组的值
        println("intArray:${intArray.joinToString()}")
        println("toList:$toList")
        // 创建一个指定大小的可空数组
        arrayOfNulls<Int>(3)
        // 这里的labdba表达式没看懂
        var array = IntArray(4){
            i : Int -> i + 2
        }
        // array:2, 3, 4, 5
        println("array:${array.joinToString()}")
        var booleanArray = BooleanArray(3)
        var arrayOfNulls = arrayOfNulls<Int>(3)
        var range1 = 1 .. 5
        for(i in range1){
            println("range1:$i")
        }
        var range2 = 1 until 5
        for(i in range2){
            println("range2:$i")
        }
        var range3 = 5 downTo 1
        for(i in range3){
            println("range3:$i")
        }
        var range4 = 1 .. 5 step 2
        for(i in range4){
            println("range4:$i")
        }
        var range5 = 1 .. 5
        var reversedRange5 = range5.reversed()
        for(i in reversedRange5){
            println("reversedRange5:$i")
        }

        var int : Int = 1
        var aaa : Long = 2L
//        var bt : Byte = int
        var byte : Byte = int.toByte()
        var long : Long = 1 + aaa
        var char : Char = '1'
        var arr = arrayOf(1 , 2)
        println("arr1: ${arr.get(0)}")
        println("arr2: ${arr[0]}")
        // for循环字符串中的每一个字符
        for(a in "12345"){
            println("aaaa:::$a")
        }

        var ttt = """ 
        测试多行字符串
        1
        2
        """
        println("ttt:::$ttt")

        var sss = """ 
        <测试多行字符串
        <1
        <2
        """.trimMargin()
        println("sss:::$sss")

        var str = "2"
        var str1 = "hahha:$str"
        var str2 = "hahhah:${str.length}"
        var str3 = "$999"
        var str4 = "${"$"}999"
        var str5 = "${'$'}999"
        println("str3:::$str3")
        println("str4:::$str4")
        println("str5:::$str5")

        var max = a
        if(a > b) max = a
        println("max:$max")

        var bbb = if(a > b) max = a else max = b
        var max1 = if(a > b) a else b
        println("maxxxxx:$max")
        println("bbbbb:$bbb")
        println("ccccc:$max1")

        var max2 = if(a > b){
            a
            println("aa:$a")
        }else {
            b
            println("bb:$b")
        }
        // max2::8 或者 max2::kotlin.Unit即无返回值
        println("max2::$max2")

        var y = 0
        var yy = 1..9
        if(y in yy){
            for(aaaa in yy){
                println("aaaaa:$aaaa")
            }
        }

        when(y){
            0 -> {
                1
                println("hahahha")
            }
            1 ->{
                println("555555555")
            }
            // 相当于 case 穿透
            3 , 4 ->{
                println("3434434343")
            }
            else -> println("papapapa")
        }

        when(y){
            in 1..10 -> println()
            !in 1..10 -> println()
            else -> println()
        }

        var arr1 = arrayOf(66 , 99 , 33 , 44 , 88)
        println("hhhhhh:${arr1[0]}")
        // 遍历数组
        for(aa in arr1){
            println("kkkkk1111:${aa}")
            haha@ for(bb in arr1){
                println("kkkk2222:${bb}")
                break@haha
            }
        }

        // 遍历数组下标
        for(dd in arr1.indices){
            println("h2222:$dd")
            println("h3333:${arr1[dd]}")
        }

        for(dd in arr1.withIndex()){
            println("h4444:${dd}")
        }

        for((index , value) in arr1.withIndex()){
            println("index:${index},value:${value}")
        }

        var oo = 5
        while(oo < 10){
            println("oo11:${oo++}")
        }

        var ii = 5
        do {
            println("doooo:${ii++}")
        }while(ii == 10)

        // 修改Person属性
        val person = Person()
        person.age = 20
        person.name = "zxc"

        Outer.Nested().aaa()

        var xxx = Test()
        xxx.setInterface(object : TestInterface{
            override fun aaa(){
            }
        })
    }

    fun test(aa : String?) : String? {
        return aa
    }

    fun test1(bb : Any) : String {
        if(bb is String){
            return "isStr"
        }else {
            return "isNotStr"
        }
    }
}

class ppp{}
class pdd
class ddd(){
    fun haha(){

    }
}

