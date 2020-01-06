package kotlinpractice.basic


import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by ruandong on 2019/12/27.
 *
 * https://www.kotlincn.net/docs/reference/idioms.html
 */
//data class User(val name: String, val age: Int) {}
//
//data class User1(var name: String, var age: Int) {}
fun filterList() {
    val list = listOf(1, 2, 3, 45, 5, 99, 5, 77, 4, 44, 55, 4, 4, 868)
    val liststr = listOf("aa", "bb", "cc", 1, 2, 3, 45, 5, 99, 5, 77, 4, 44, 55, 4, 4, 868)
    val x = list.filter { it > 20 }
    println(x)

    if ("cc" in liststr) {
        println("cc at index # ${liststr.indexOf("cc")}")
    }

    var xxx = 9
    when (xxx) {
        is Int -> println("$xxx is a int ")
        else -> "else "
    }
//    val reaonlyMap = mapOf("a" to 1, "b" to 2, "c" to 3)
    var map = hashMapOf<Int, Int>()
    map[1] = 11
    map[2] = 22
    map[3] = 33

    for ((k, v) in map) {
        println("$k--->$v")
    }
}

fun String.print() {
    println(this)
}

fun exFunc() {
    //扩展函数
    var str = "this is a text"
    str.print()
}

fun nullCheck(p: Any?) {

    p?.let {
        print("$p is not null")
        return
    }

    println("p is null")

}


fun transform(i: Int): String {

    return when (i) {
        1 -> "Monday"
        2 -> "Tuesday"
        else -> "invalid input"
    }
}


fun test() {

    val result = try {
        count()
    } catch (e: ArithmeticException) {
    }
    println(result)
}

fun count() {
    println(1 + 8)
}

fun foo(params: Int) {
    val result = if (params == 1) {
        "one"
    } else if (params == 2) {

        "tow"
    } else {
        "three"
    }

    print(result)
}


fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}

const val a = 55
fun theAnswer() = a
fun theAnswer1() = 22

class Baymax() {

    fun charge() {
        println("charge")
    }

    fun move() {
        println("move")
    }

    fun sleep() {
        println("sleep")
    }

}

class Rectangle() {

    var height: Int = 0
    var length: Int = 0

}

fun readFiles() {

    val stream = Files.newInputStream(Paths.get("file.txt"))

    stream.buffered().reader().use { reader-> println(reader.readText()) }

}

fun main() {


    readFiles()

//    val rectangle = Rectangle().apply {
//        height = 10
//        length = 20
//    }
//    println(rectangle.height)
//    println(rectangle.length)
//    val baymax = Baymax()
//    with(baymax) {
//        charge()
//        move()
//        sleep()
//    }

//    print(theAnswer1())
//    print(theAnswer())
//    arrayOfMinusOnes(4)
//    foo(4)
//    test()
//    println(transform(2))
//    nullCheck(151515)
//    exFunc()
//    filterList()
//    var user = User("name", 8)
//    var user1 = User1("user1", 19)
//    user1.name = "jj"
}




