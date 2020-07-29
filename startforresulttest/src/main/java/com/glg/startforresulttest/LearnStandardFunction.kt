package com.glg.startforresulttest

/**
 * Created by gao on 2020/7/14 15:05.
 * Function:
 */
fun main() {
    //当我们需要将某一函数体执行多次时，可以使用repeat（）函数，传入的数值是多少，系统就会将其中的代码执行多少次
    //例如：var a = 0     repeat(10) { a ++ }

    //当我们重写某个方法时，方法体中只使用到了部分所传递的参数，这个时候就可以将没有用到的参数使用下划线代替，注意参数的位置不能改变


    //当我们在代码中多次使用到同一个对象的方法时，就可以使用with函数对代码进一步简化
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val builder = StringBuilder()
    builder.append("Start eating fruits.\n")
    for(fruit in list) {
        builder.append(fruit).append("\n")
    }
    builder.append("Ate all fruits.")
    val result = builder.toString()
    println(result)
    //使用with函数进行简化
    val result1 = with(StringBuilder()){
        append("Start eating fruits.\n")
        for(fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        toString()
    }
    println(result1)


    //run函数与with用法非常的相似，run函数一定要调用某个对象的run函数，其次run函数只接收一个Lambda参数，并且会在Lambda表达式中提供调用对象的上下文
    val result2 = StringBuilder().run {
        append("Start eating fruits.\n")
        for(fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        toString()
    }
    println(result2)


    //apply函数和run函数也是极其类似的，都要在某个对象上调用，并且只接收一个Lambda参数，也会在Lambda表达式中提供调用对象的上下文，但是apply函数无法指定返回值，而是会自动返回调用对象本身
    val result3 = StringBuilder().apply {
        append("Start eating fruits.\n")
        for(fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        toString()
    }
    println(result3.toString())


    //kotlin中没有静态方法的关键字，想实现静态方法的效果可以使用单例类或者将某个方法声明到companion object当中，就可以直接使用类名进行调用了
    //与java语言不同的是，即使将某个方法声明到了companion object当中，该方法也不是静态方法，而是在该类内部创建了一个伴生类，只是kotlin会保证一个类只有一个伴生类对象，实际上就是调用这个伴生类对象的方法
    //kotlin中如果将方法声明为静态方法，可以使用注解和顶层方法
    //将一个方法声明为静态方法，可以给单例类或者companion object中的方法加上@JvmStatic注解
    //顶层方法是没有声明在任何一个类中的方法，kotlin编译器会将所有的顶层方法编译成静态方法
    //我们在.kt后缀名文件中创建的方法就是顶层方法，在kotlin代码中哪里都可以直接调用，在java类中调用时使用静态方法的调用语法即可
}