package com.glg.startforresulttest

import android.util.Log
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.reflect.KProperty

/**
 * Description:
 *
 * @package: com.glg.startforresulttest
 * @className: HigherOrderFunction
 * @author: gao
 * @date: 2020/8/19 17:41
 */

@ExperimentalContracts
fun main() {
    val num1 = 100
    val num2 = 80
    val result1 = num1AndNum2(num1, num2, ::plus)
    val result2 = num1AndNum2(num1, num2, ::minus)
    println("result1 is $result1")
    println("result2 is $result2")

    //Lambda表达式会默认将最后一行作为返回值，下列写法就可以不声明与所需要的函数类型参数相匹配的函数了，也就是不需要plus函数和minus函数了，得到的效果与前面的一模一样
    val result3 = num1AndNum2(num1, num2) {
        n1, n2 -> n1 + n2
    }
    val result4 = num1AndNum2(num1, num2) {
        n1, n2 -> n1 - n2
    }
    println("result3 is $result3")
    println("result4 is $result4")

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())


    println("main start")
    val str = ""
    printString(str) { s ->
        println("lambda start")
        if(s.isEmpty()) return@printString//Lambda表达式中是不允许直接使用return关键字的 ，这里使用了return@printString的写法，表示进行局部返回，自然就不会继续执行Lambda表达式的剩余部分代码
        println(s)
        println("lambda end")
    }
    println("main end")
    //如果将printString()函数声明成inline函数，那么在Lambda表达式中就可以使用return关键字了 ，而且返回的是外层调用函数，此处表示的就是main()函数

    //此处调用build方法时，优先使用的是StringBuilder类的build()方法
    println(java.lang.StringBuilder()?.build {
        append("hello")
        println("kotlin泛型使用")
        return@build
    }.toString())

    var a = MyClass()
    a.p = "嗯嗯"
    println(a.p)

    //此处的log是不会被打印出来的，因为使用了懒加载，只有当第一次使用的时候才会进行加载执行
    val p by later {
        Log.d("测试", "run codes inside later block")
        "test later"
    }

    println("Hello Kotlin" beginsWith "Hello")

    val map = mapOf("Apple" with 1, "Banana" with 2, "Orange" with 3, "Pear" with 4, "Grape" with 5)

    val result01 = getGenericType<String>()
    val result02 = getGenericType<Int>()
    println("result01 is $result01")
    println("result02 is $result02")

    check(false, {
        "哈哈哈"
        //return@check "lalala"
        return
        "hahaha"
    })
    println("测试")

}

//将高阶函数声明成内联函数时一种良好的变成习惯，事实上，绝大多数高阶函数是可以直接声明成内联函数的，但是也有例外的情况
//这段代码在没有加上inline关键字声明的时候绝对是可以正常工作的，但是在加上inline关键字之后就会报错了，这里使用了crossinline关键字就可以正常编译通过了
//这种报错的原因是因为：内联函数的Lambda表达式中允许使用return关键字，高阶函数的匿名类实现中不允许使用return关键字，crossinline也只是让其通过编译，只要使用该函数的时候不要在内联函数的Lambda表达式中使用return关键字即可
//声明了crossinline以后，我们调用该函数的时候就无法在Lambda表达式中使用return关键字进行返回了，但是仍然可以使用return@runRunnable来进行局部返回
inline fun runRunnable(crossinline block: () -> Unit) {
    val runnable = Runnable {
        block()
    }
    runnable.run()
}

//(String, Int) -> Unit   函数类型左边是用来声明接收的参数类型，多个参数之间有逗号隔开，右边是表示返回值类型，无返回值就使用Unit
//kotlin中增加了函数类型，将函数类型添加到某个函数的参数声明或者返回值声明上，那么这个函数就是一个高阶函数了
fun example(func: (String, Int) -> Unit) {
    func("hello", 123)
}

//内联函数能够有效的消除Lambda表达式所带来的运行时开销
inline fun num1AndNum2(num1: Int, num2:Int, operation: (Int, Int) -> Int):Int {
    val result = operation(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

//这里定义给StringBuilder类定义了一个build扩展函数， 这个扩展函数接收一个函数类型参数，并且返回值类型也是StringBuilder
//fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
//    block()
//    return this
//}

fun printString(str: String, block: (String) -> Unit) {
    println("printString begin")
    block(str)
    println("printString end")
}


//kotlin中的可变长参数可使用vararg关键字进行实现，然后在函数体内对其进行遍历处理即可


//kotlin中泛型的使用
fun <T> T.build(block: T.() -> Unit) : T {
    block()
    return this
}


//kotlin中的委托包括类委托和委托属性，类委托使用的是by关键字
//类委托的核心思想是将一个类的具体实现委托给另外一个类来进行完成，而委托属性的核心思想是将一个属性（字段）的具体实现委托给另外一个类进行完成
class MySet<T>(val helperSet : HashSet<T>) : Set<T> by helperSet {

    fun helloWorld() = println("Hello World")
}
//该类将与HashSet类中相同的方法全部委托给HashSet类来进行处理，并且声明了HashSet类中没有的helloWorld（）方法

//在MyClass类中将p属性的具体实现委托给了Delegate去完成，为p属性获取和赋值就会调用Delegate类的getValue()和setValue()方法
class MyClass {
    var p by Delegate()
}

//这是一种标准的代码实现模板，在Delegate类中我们必须实现getValue()和setValue()方法，并且都要使用operator关键字进行声明
//getValue()方法接收了两个参数，第一个表示可以在哪个类中进行使用，第二个参数KProperty<*>时Kotlin中的一个属性操作类，可用于获取各种属性相关的值，此处用不到这个属性，但是必须在方法参数上声明
//<*>这种泛型的写法表示你不知道或者不关心泛型的具体类型，只是为了通过语法编译而已，有点类似于Java中<?>的写法。返回值可以声明成任意类型
//setValue()方法接收了三个参数，前两个参数表示的含义与getValue()方法相同，第三个参数表示具体要赋值给委托属性的值，这个参数的类型必须与getValue()方法的返回值保持一致
class Delegate {
    var propValue: Any? = null

    operator fun getValue(myClass: MyClass, prop: KProperty<*>) : Any? {
        return propValue
    }

    operator fun setValue(myClass : MyClass, prop: KProperty<*>, value : Any?) {
        propValue = value
    }
}

class Later<T>(val block: () -> T) {
    var value: Any? = null
    operator fun getValue(any: Any?, prop: KProperty<*>) : T {
        if(value == null) {
            value = block()
        }
        return value as T
    }
}

fun <T> later(block : () -> T) = Later(block)


//infix函数是不能定义成顶层函数的，它必须是某个类的成员函数，且infix函数只能接收一个参数，必须满足这两点，infix函数的语法糖才具备使用的条件
//infix语法糖：A to B = A.to(B)
infix fun String.beginsWith(prefix: String) = startsWith(prefix)

infix fun <T> Collection<T>.has(element: T) = contains(element)//这样所有实现了Collection接口的类都可以调用has函数了，功能与contains函数是相同的

infix fun <A, B> A.with(that: B): Pair<A, B> = Pair(this, that)//这样我们就可以使用with()函数构建键值对了，还可以将构建的键值对传入mapOf()方法当中


//泛型实化需要满足必须是内联函数，泛型必须被reified关键字进行修饰
inline fun <reified T> getGenericType() = T::class.java

//测试
@ExperimentalContracts
inline fun check(value: Boolean, lazyMessage: () -> Any): Unit {
    contract {
        returns() implies value
    }
    if (!value) {
        val message = lazyMessage()
        throw IllegalStateException(message.toString())
    }
}