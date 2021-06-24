package com.zhr.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zhr.test.bean.Person
import com.zhr.test.bean.Person1
import com.zhr.test.bean.Person2
import com.zhr.test.bean.Person3

@Route(path = AppConstants.APP_SEC)
class SecActivity : AppCompatActivity() {

    // 如果想要常量的话，需要用@JvmField注解，或者const关键字
    @JvmField
    val TAG: String = "SecActivity"

    /**
     * 第四步：Arouter传递过来的数据如果要使用的话，直接用常量来接收就可以了
     * 为每一个参数声明一个字段，并使用 @Autowired 标注
     * URL中不能传递Parcelable类型数据，通过ARouter api可以传递Parcelable对象
     * 如果需要接收Arouter传递过来的参数，需要在接收的类中调用Arouter的inject方法
     */
    @Autowired
    @JvmField
    var name: String? = null

    /**
     * 这里的required表示的是 是否为预留字段
     * false 表示为预留字段  true表示为必需字段
     * 经测试不传过来字段，kotlin写也不会报错，估计Java会报错吧，明确知道的预留字段写成required就好
     */
    @Autowired(required = false)
    @JvmField
    var age: Int = 0

    @Autowired
    @JvmField
    var boy: Boolean = false

    @Autowired
    @JvmField
    var person1: Person1? = null

    /**
     * 如果@Autowired这里不想要自定义名称
     * 这里的变量名称就要和传递的变量名称一致
     */
    @Autowired
    @JvmField
    var person2: Person2? = null

    /**
     * 如果@Autowired这里想要自定义名称，就可以加一个name参数传入实际名称，
     * 后面就可以使用自定义名称了
     */
    @Autowired(name = "person3")
    @JvmField
    var person3333: Person3? = null

    @Autowired
    @JvmField
    var person: Person? = null

    @Autowired
    @JvmField
    var personList: List<Person>? = null

    @Autowired
    @JvmField
    var personMap: Map<String, Person>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)

        ARouter.getInstance().inject(this)
        findViewById<TextView>(R.id.tv_sec).setOnClickListener {
//            Log.e(TAG, "name:$name,age:$age")
            // 接收基本数据类型
            Log.e(TAG, "name:${name}, age:${age}, boy:${boy}")
            // 接收Parcelable类型的对象1 对象赋值的方式不同 一个是构造赋值一个是get set赋值
            Log.e(TAG, "person1_name:${person1?.name}, person1_age:${person1?.age}")
            // 接收Parcelable类型的对象2
            Log.e(TAG, "person2:${person2}")
            // 接收Serializable类型的对象
            Log.e(TAG, "person3_name:${person3333?.name}, person3_age:${person3333?.age}")
            // 接收Object类型的对象
            Log.e(TAG, "person_name:${person?.name}, person_age:${person?.age}")
            // 接收Object类型的集合对象  如果想要打印出正常的对象数据 需要重写toString方法 直接打印对象就是打印了对象的toString方法
            Log.e(TAG, "personList:${personList.toString()}")
            // 接收Map类型的集合对象
            Log.e(TAG, "personMap:${personMap}")

            // 通过Intent 获取传递过来的值 "name"
            var name: String? = getIntent().getStringExtra("name")
            Log.e(TAG, "onCreate: $name")
        }
    }
}