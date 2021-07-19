package com.zhr.test

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zhr.test.bean.Person
import com.zhr.test.bean.Person1
import com.zhr.test.bean.Person2
import com.zhr.test.bean.Person3

/**
 * 案例六：ARouter的使用
 * https://github.com/alibaba/ARouter/blob/master/README_CN.md
 * 1、添加基础依赖和配置
 * module的build.gradle中配置api依赖 和kapt（java和kotlin配置的方法不一样，这里使用的是kotlin的配置方法）
 * 2、初始化ARouter第三方库
 * 自定义Application并初始化ARouter，在AndroidManifest中引入Application
 * 也可以在使用时Activity中初始化
 * 3、在Activity上添加注解
 * 在Activity中进行跳转：在支持路由的页面上添加注解 ，这里的path路径需要对应的有两个
 * 一个是发送端地址，一个是接收端地址；path路径格式一般为/module名/类名
 * 要跳转的两个Activity需要在AndroidManifest文件中进行声明
 * 4、发起路由操作
 * 几种路由跳转方式以及路由发送数据的接收
 * 5、添加混淆规则
 * 这是一个最基本的使用案例，如果要更详细的，可以找自己测试过的ARouter官方Demo
 */
/**
 * 第三步：在支持路由的页面上添加注解，这里的path路径需要对应的有两个，一个是发送端地址，一个是接收端地址
 * path路径格式一般为/module名/类名
 */
@Route(path = AppConstants.APP_MAIN)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 第四步：发起路由操作
        findViewById<TextView>(R.id.tv_main).setOnClickListener {
            // 路由跳转方式1：Path跳转
//            ARouter.getInstance().build(AppConstants.APP_SEC).navigation()

            // 一个不成熟的想法，局部变量里面val是可变的，成员变量里面val是不可以变的
            val person2: Person2 = Person2()
            person2.name = "ghm"
            person2.age = 100

            // listOf创建的集合数据是不能修改的，mutableListOf创建的数据是可以修改的
            val personList = mutableListOf<Person>()
            val p1 = Person("p1", 1)
            val p2 = Person("p2", 2)
            personList.add(p1)
            personList.add(p2)

            val personMap = mutableMapOf<String, Person>()
            personMap.put("1", Person("M1", 1))
            personMap.put("2", Person("M2", 2))

            val bundle = Bundle()
            bundle.putParcelable("bundle", Person1("p1", 1))

            // 路由跳转方式1：Path携带参数跳转
            ARouter.getInstance().build(AppConstants.APP_SEC)
                    .withString("name", "test")
                    .withInt("age", 22)
                    .withBoolean("boy", true)
                    .withParcelable("person1", Person1("zhr", 21))
                    .withParcelable("person2", person2)
                    .withSerializable("person3", Person3("lll", 50))
                    .withObject("person", Person("hhh", 100))
                    .withObject("personList", personList)
                    .withObject("personMap", personMap)
                    // todo 没有研究出来Bundle类型传递过去如何接收
//                    .with(bundle)
                    .navigation()

            // 路由跳转方式2：Uri跳转
//            ARouter.getInstance().build(Uri.parse(AppConstants.APP_SEC)).navigation()

            // 这段代码是废的，没有实际意义，留着只是为了学习Kotlin语法
            // 如果想要知道最后得到的数据类型是什么，那就看最后一个属性的返回值是什么
            val postCard: Postcard = ARouter.getInstance().build(AppConstants.APP_SEC)
            LogisticsCenter.completion(postCard);
            // 这里的Class<?> 最新返回的是Class<*>
            val destination: Class<*> = postCard.destination

        }
    }
}