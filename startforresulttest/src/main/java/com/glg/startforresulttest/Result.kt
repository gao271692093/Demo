package com.glg.startforresulttest

/**
 * Created by gao on 2020/7/21 14:48.
 * Function:
 */
interface Result
class Success(val msg: String) : Result
class Failure(val error: Exception) : Result

fun getResultMsg(result : Result) = when(result) {
    is Success -> result.msg
    is Failure -> result.error.message
    else -> throw IllegalArgumentException()
}

//注意：密封类及其所有子类只能定义在同一个文件的顶层位置，不能嵌套在其他类中，这是被密封类底层的实现机制所限制的
sealed class Result1
class Success1(val msg: String) : Result1()
class Failure1(val error: Exception) : Result1()

fun getResultMsg(result : Result1) = when(result) {
    is Success1 -> result.msg
    is Failure1 -> "Error is ${result.error.message}"
}