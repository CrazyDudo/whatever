import kotlin.math.sqrt

/**
 * Created by ruandong on 2019/12/26.
 *
 * https://www.kotlincn.net/docs/reference/basic-syntax.html
 * https://huanglizhuo.gitbooks.io/kotlin-in-chinese/content/GettingStarted/Basic-Syntax.html
 */


fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}


fun stringTamplate() {

    var a = 1
// simple name in template:
    val s1 = "a is $a"

    a = 2
// arbitrary expression in template:
    val s2 = "${s1.replace("is", "was")}, but now is $a"

    print(s2)
}


fun maxOf(a: Int, b: Int) = if (a > b) a else b


fun parseInt(str: String): Int? {
    // ...

    return str.toInt()
}

fun getStringLegth(obj: Any): Int? {


    if (obj is String) {
        return obj.length
    }


    return null
}

fun loop() {
    val items = listOf("apple", "banana", "kiwifruit", 1, false, null)
    for (item in items) {
        println(item)
    }


}

fun whenTest(obj: Any) = when (obj) {
    1 -> "one"

    2 -> ""

    "ss" -> "str"

    else -> null
}

fun range() {
    val x = 9
    val y = 10

    if (x in 1..y) {
        print("x in 1 - y")
        return
    }
    print("x not in 1 - y")
}

fun controlFlow() {


    for (i in 1..6) {
        print(i)

    }

    for (i in 10 downTo 1 step 2) {
        println(i)
    }


    val items = listOf("a", "b", "c", "c")


    for (item in items) {
        println(item)
    }
}

fun filter() {

    val fruits = listOf("apple", "ms", "google", "fb", "amazon")

    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
}


//创建基本类及其实例
abstract class Shape(val sides: List<Double>) {

    val perimeter: Double get() = sides.sum()
    abstract fun caculateArea(): Double
}

class Rectangle(private var height: Double, private var length: Double) :
    Shape(listOf(height, length, height, length)) {
    override fun caculateArea(): Double = height * length
}

class Triangle(var sideA: Double, var sideB: Double, var sideC: Double) :
    Shape(listOf(sideA, sideB, sideC)) {
    override fun caculateArea(): Double {
        val p = perimeter / 2
        return sqrt(p * (p - sideA) * (p - sideB) * (p - sideC))
    }
}

fun main() {
    var rectangle = Rectangle(2.0, 3.0)
    println("rectangle周长是==${rectangle.perimeter}")
    println("rectangle面积是==${rectangle.caculateArea()}")

    var triangle = Triangle(3.0, 4.0, 5.0)
    println("triangle周长是==${triangle.perimeter}")
    println("triangle面积是==${triangle.caculateArea()}")

//    filter()
//    controlFlow()
//    range()
//    var x:Any = 0
//    val b = x is String
//    print(b )

//    print(whenTest("ss"))
//    loop()
//    val x = 9
//    print("x is $x")
//    print(getStringLegth("iaf"))
//    print("test")
//    printSum(3, 4)
//    stringTamplate()
//    print(maxOf(2, 2))
//    print(parseInt("033"))
}

