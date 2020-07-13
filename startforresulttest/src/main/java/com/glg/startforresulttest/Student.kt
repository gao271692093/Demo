package com.glg.startforresulttest

/**
 * Created by gao on 2020/7/8 17:19.
 * Function:
 */
//kotlin中一个类默认是有主构造函数的，当然也可以显式给出
//例如：class Student(val sno : String, val grade : Int) : Person() {}
//如果想在主构造函数中进行一些逻辑的编写，可以在类中添加init{}，在init中进行想要的各种逻辑编写
//例如：class Student(val sno : String, val grade : Int) : Person() {init {println(“sno is " + sno)  println("grade is " + grade)}}
class Student(val sno: String, val grade: Int, name : String, age : Int):Person(name, age) {
    init {
        println("sno is " + sno)
        println("grade is " + grade)
    }
    //kotlin规定，当一个类既有主构造函数又有次构造函数时，所有的次构造函数都必须调用主构造函数（包括间接调用）。
    constructor(name : String, age : Int) : this("", 0, name, age) {

    }

    constructor() : this ("", 0) {//该构造函数通过调用第一个词构造函数来间接调用主构造函数，所以也合法

    }
}
//一个类也可以只有次构造函数，只有次构造函数时，在集成其他类的时候就不需要加上一对括号了
//例如：
//class  Student : Person {
//    constructor(name : String, age : Int) : super(name, age) {
//
//    }
//}