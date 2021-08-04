package com.example.myapplication

fun main(args: Array<String>) {
    filterAgeAddrHeight()
    filterMaxAge()
}

fun filterAgeAddrHeight() {
    var suit = ArrayList<Beauty>()
    for(beauty in beautys){
        if(beauty.age < 30 && beauty.address == "湖北" && beauty.height > 165){
            suit.add(beauty)
        }
    }
    println("suit:::$suit")
}

fun filterMaxAge(){
    var suit = ArrayList<Beauty>()
    var maxAge = 0
    // 找出最大的年龄
    for(beauty in beautys){
        val age = beauty.age;
        if(age > maxAge){
            maxAge = age
        }
    }
    println("maxAge:::$maxAge")
    // 根据最大年龄再去找人
    for(beauty in beautys){
        if(beauty.age == maxAge){
            suit.add(beauty)
        }
    }
    println("suit:::$suit")

    val maxAge1 = beautys.maxBy { it.age }
    // 返回年龄最大的那个Bean
    println("maxAge:::$maxAge1")
    println("minHeight:::${beautys.minBy { it.height }}")

    var filterBeauty = beautys.filter { it.age > 30 && it.height > 165}
    println("filterBeauty:$filterBeauty")
    for(aa in filterBeauty){
        if(aa.name == "赵六") println("aa:$aa")
    }

    // 如果年龄最大的有多个时 先maxby 再filter
    var maxBy = beautys.maxBy { it.age }
    val filter = beautys.filter { it.age == maxBy?.age }
    println("filter:$filter")

    val maps = beautys.map { "${it.name} ${it.age} ${it.height}" }
    println("map$maps")
    for(m in maps){
        println(m)
    }

    val groupBy = beautys.groupBy { it.address }
    for(gb in groupBy){
        val key = gb.key
        val value = gb.value
        println("key:$key ,,, value:$value")
    }

    val get = beautys.groupBy { it.name }.get("aaa")
    println("get:::$get")
    groupBy.forEach { println(it)}
}
