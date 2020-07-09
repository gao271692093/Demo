package com.glg.startforresulttest

/**
 * Created by gao on 2020/7/8 18:08.
 * Function:
 */
//kotlin也有四种可见性修饰符：private、public、protected、internal，默认为public，protected表示只允许当前类和子类可见，internal表示只有同一模块中的类可见
class Stu(name : String, age : Int) : Person(name, age), Study {
    override fun readBooks() {
        println(name + " is reading.")
    }

    override fun doHomework() {
        println(name + "is doing homework.")
    }
}