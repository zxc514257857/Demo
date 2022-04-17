package com.example.myapplication

data class Beauty(
    val name : String?,
    val age : Int,
    val address : String?,
    val height : Int
)

var beautys = listOf<Beauty>(
    Beauty("张三" , 28 , "湖北" , 175),
    Beauty("李四" , 18 , "湖南" , 160),
    Beauty("王五" , 40 , "河南" , 158),
    Beauty("赵六" , 35 , "河北" , 170),
    Beauty("燕七" , 40 , "浙江" , 168)
)