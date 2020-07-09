package com.glg.startforresulttest

/**
 * Created by gao on 2020/7/8 16:54.
 * Function:
 */
//这种方式是有默认构造函数的，如果显式给出构造函数，那么子类在继承时就会因为父类没有无参构造而报错
//如果显式给出Person类的构造函数时，子类想继承Person类就需要将Person类的属性也传入，例如
//open class Person（var name String, var age Int){}
//class Student(val sno: String, val grade: Int, name : String, age : Int):Person(name, age) {...}
//注意此时就不能将name和age声明成val，因为在主构造函数中声明成val或者var的参数将自动成为该类的字段，这就会导致和父类中同名的name和age字段造成冲突
//open class Person {
//    var name = ""
//    var age = 0
//
//    fun eat() {
//        println(name + " is eating. He is  " +age + " uears old.")
//    }
//}
open class Person(var name : String, var age : Int){
    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }
}