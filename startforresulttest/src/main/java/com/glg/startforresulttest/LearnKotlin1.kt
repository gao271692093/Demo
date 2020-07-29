package com.glg.startforresulttest

/**
 * Created by gao on 2020/7/21 14:38.
 * Function:
 */
fun main() {
    //使用lateinit关键字对变量进行延迟初始化
    lateinit var student : Student
    //判断变量是否被初始化的语法：      ：：变量名.isInitialized

    //密封类的关键字是 sealed class,用法参考Result.kt

    //扩展函数的使用，语法：fun ClassName.methodName(param1 : Int, param2 : Int) : Int { return 0 }
    //相比于定义一个普通的函数，定义扩展函数只需要在函数名的前面加上一个ClassName.的语法结构，就表示将该函数添加到指定类当中了
    val str = "ABC123xyz!@#"
    val count = StringUtil.lettersCount(str)
    val count1 = str.lettersCount()             //kotlin中的String还有扩展函数：reverse()用于反转字符串、capitalize()用于对首字母进行大写等等

    //运算符重载使用的是operator关键字，只要在自定函数的前面加上operator关键字，就可以实现运算符重载的功能了
    //语法：  class Obj { operator fun plus(obj : Obj) : Obj { //处理相加的逻辑 } }   //这样Obj这个类的对象就可以支持相加操作了
    //语法糖表达式和实际调用函数对照表：
    //a+b:a.plus(b)、a-b:a.minus(b)、a*b:a.times(b)、a/b:a.div(b)、a%b:a.rem(b)、a++:a.inc()、a--:a.dec()、+a:a.unaryPlus()、-a:a.unaryMinus()、!a:a.not()、
    //a == b:a.equals(b)、a > < >= <= b: a.compareTo()、a..b:a.rangeTo(b)、a[b]:a.get(b)、a[b] = c:a.set(b, c)、a in b:b.contains(a)
    //例如：if("hello".contains("he"))   等同于 if("he" in "hello")

    class Money(val value : Int){
        operator fun plus (money : Money) : Money {
            val sum = value + money.value
            return Money(sum)
        }

        operator fun plus(newValue : Int) : Money {
            val sum = value + newValue
            return Money(sum)
        }
    }
    val money = Money(5) + Money(10)
    println(money.value)
    val money1 = Money(7) + 10
    println(money1.value)

    println(str * 3)

    println(getRandomLengthString(str))

}

//将lettersCount()函数添加到String类中就可以这样写
//此时因为String类已经有了该方法，所以函数中就自然有了上下文，就不需要再接收一个参数了，直接遍历this即可
fun String.lettersCount() : Int{
    var count = 0
    for(char in this) {
        if(char.isLetter()) {
            count++
        }
    }
    return count
}

object  StringUtil {
    fun lettersCount(str : String) : Int {
        var count = 0
        for(char in str) {
            if(char.isLetter()) {
                count++
            }
        }
        return count
    }
}
//由于Kotlin的String类中已经提供了一个用于将字符串重复n遍的repeat()函数，因此times()函数可以进一步精简成：operator fun Stringtimes(n: Int) = repeat(n)
operator fun String.times(n : Int) : String {
    val builder = StringBuilder()
    repeat(n) {
        builder.append(this)
    }
    return builder.toString()
}

fun getRandomLengthString(str : String) = str * (1..20).random()