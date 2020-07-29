package com.glg.startforresulttest

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Integer.max

/**
 * Created by gao on 2020/7/8 15:09.
 * Function:
 */

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    println("Hello Kotlin!")
    val a = 10;//val表示变量不可变，var表示变量可以被重新赋值
    println("a=" + a)
    val value = largerNumber(37, 40)
    println("larger number is " + value)

    //android中强制类型转换使用as关键字

//    var p = Person()
//    p.name = "Jack"
//    p.age = 19
//    p.eat()
    var student1 = Student()
    var student2 = Student("Jack", 19)
    var student3 = Student("a123", 5, " Jack", 19)

    var student = Stu("Jack", 19)
    doStudy(student)

    val cellphone1 = Cellphone("Samsung", 1299.99)
    val cellphone2 = Cellphone("Samsung", 1299.99)
    println(cellphone1)
    println("cellphone1 equals cellphone2 " + (cellphone1 == cellphone2))

    //list测试
//    val list = ArrayList<String>()
//    list.add("Apple")
//    list.add("Banana")
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "WaterMelon")
    for(fruit in list) {
        println(fruit)
    }
    //listof()创建的是一个不可变的集合，创建可变的list使用mutableListof()函数进行创建

    //set的用法与list的用法完全一致，创建函数使用setOf()和mutableSetOf()

    //map测试
    val map = HashMap<String, Int>()
    map.put("Apple", 1)
    map.put("Banana", 2)
    //lotlin并不推荐使用put和get方法进行增加和删除元素,而推荐以下写法
    map["Orange"] = 3
    val number = map["Orange"]
    //kotlin也提供了mapOf()函数和mutableMapOf()函数
    val map1 = mapOf<String, Int>("Apple" to 1, "Banana" to 2, "Orange" to 3)
    for((fruit, number) in map) {
        println("fruit is " + fruit + ", number is " + number)
    }

    //想找出一个list中长度最大元素，可以使用普通写法，也可以使用list.maxBy{ it.length }

    //Lambda表达式的语法结构：{参数名1:参数类型, 参数名2:参数类型 -> 函数体}

    val lambda = {fruit: String -> fruit.length}
    var maxLengthFruit = list.maxBy(lambda)   //可直接将lambda表达式放进maxBy函数中
    //当lambda参数是函数的最后一个参数时，可以将lambda参数放到括号外边
    maxLengthFruit = list.maxBy(){fruit : String -> fruit.length}
    //如果Lambda参数是函数的唯一一个参数时，可以将括号省略
    maxLengthFruit = list.maxBy {fruit : String -> fruit.length}
    //由于kotlin有出色的类型推导机制，可以不必声明参数类型
    maxLengthFruit = list.maxBy {fruit -> fruit.length}
    //当Lambda表达式的参数只有一个时，可以不必声明参数名，直接使用it关键字
    maxLengthFruit = list.maxBy {it.length}


    //map函数可以将集合中的每个元素都映射成一个另外的值，映射的规则在Lambda表达式中指定，最终生成一个新的集合
    //比如我们可以将list中的水果名都变成大写模式
    var newList = list.map{it.toUpperCase()}


    //filter是用来过滤集合中数据的，也可以配合map函数一起使用，比如我们想只保留5个字母以内的水果
    newList = list.filter { it.length <=5 }.map { it.toUpperCase() }


    //any和all函数的使用，any函数用于判断集合中是否有一个满足指定条件，all函数用于判断集合中是否所有元素都满足指定条件
    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }


    Thread(object : Runnable {
        override fun run() {
            println("Thread is running")
        }
    }).start()

    //如果一个Java方法的参数列表中不存在一个以上Java单抽象方法接口参数，我们可以将接口名省略
    Thread({
        println("Thread is running")
    }).start()

    Thread{ println("Thread is running")}.start()   //最终可简化成该写法

    //利用java函数式api很多时候可以进一步简化代码，例如：为一个button添加点击事件：button.setOnclickListener{}


    //使用kotlin的判空辅助工具，如：if（a!=null) { a.doSomething() } 可以简化为：a？.doSomething()
    //val c = a ?: b  表示a不为空时将c赋值为a，a为空时将c赋值为b
    //fun getTextLength(text: String?) = text?.length ?:0   该函数表示text为空时返回0，不为空时返回text的长度
    //非空断言工具的使用，例如：content!!/toUpperCase()  这里就表示content不可能为空，编译器也就不会对其进行空指针检查了


    //let函数的使用，例如： fun doStudy(study： Study？) { study?.readBooks()  study?.doHomework() }  可以简化为： fun doStudy(study: Study?) { study?.let{stu -> stu.readBooks()  stu.doHomework()} }
    //let函数会将对象本身传递到Lambda表达式中，上述例子中stu其实就是study对象，只是为了防止重名而改为了stu，再利用Lambda表达式的语法特性可继续简化为：  fun doStudy(study : Study?){ study?.let{ it.readBooks()  it.doHomework() } }


    //内嵌表达式的使用
    var p = Person("Jack", 19)
    println("Hello,${p.name}. Nice to meet you!")
    println("Hello,$p.name. Nice to meet you!")//当表达式中只有一个变量时，可以将一对大括号省去


    //kotlin可以给函数的参数设置默认值，当调用时没有传递该参数时会自动使用默认值
    //使用函数的参数默认值时，注意传递的参数如果不是第一个参数时要使用键值对的方式进行传参，例如：printParams(str = "world")
    //该技巧可以将之前写的Student类简化为：class Student(val sno: String =  "", val grade : Int = 0, name : String = "", age : Int  = 0) : Person(name, age) {}  这样就可以使用任意参数组合进行Student的实例化了

}

    fun doStudy(study : Study) {
        study.readBooks()
        study.doHomework()
    }

//@RequiresApi(Build.VERSION_CODES.N)
//fun largerNumber(num1 : Int, num2 :Int): Int {
//    return max(num1, num2);
//}

@RequiresApi(Build.VERSION_CODES.N)
fun largerNumber(num1 : Int, num2 : Int) : Int = max(num1, num2)//当一个函数只有一行函数体的时候就可以使用这种声明方式

fun largerNumber1(num3 : Int, num4 : Int) : Int{
    var value = 0
    if(num3 > num4) {
        value = num3
    } else {
        value = num4
    }
    return value
}

fun largerNumber2(num3 : Int, num4 : Int) : Int {
    var value = if(num3 > num4) {
        num3
    } else {
        num4
    }
    return value
}//if-else与java的语法几乎一致，不同的是kotlin中的if-else可以带返回值

fun largerNumber3(num3 : Int, num4 : Int) : Int {
    return if(num3 > num4) {
        num3
    } else {
        num4
    }
}

fun largerNumber4(num3 : Int, num4 : Int) : Int = if(num3 > num4) {num3} else {num4}
//largerNumber1、largerNumber2、largerNumber3、largerNumber4所表达的意思完全相同

fun getScore(name : String) = if(name == "Tom") {
    83
} else if(name == "Jim") {
    77
} else if (name == "Jack") {
    95
} else if(name == "Lily") {
    100
} else {
    0
}

//getScore1与getScore表达的意思完全一致，when也可以带返回值
fun getScore1(name : String) = when(name) {
    "Tom" -> 83
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}

//when有时候必须使用以下方法实现
fun getScore2(name : String) = when {
    name.startsWith("Tom") -> 86
    name == "Jim" -> 88
    else ->0
}

//kotlin中is关键字就跟java中的instanceof关键词的作用相似
fun checkNumber(num : Number) {
    when(num) {
        is Int -> println("number is  Int")
        is Double ->println("number is Double")
        else -> println("number not support")
    }
}

//kotlin中可以定义一个区间，例如：val range = 0..10，其表示0到10的闭区间，遍历时可以使用for（i in 0..10），想设置步长时在后边加上step
//val range = 0 until 10 表示0到10的左闭右开区间
//val range = 10 downTo 1 表示10到1的降序闭区间