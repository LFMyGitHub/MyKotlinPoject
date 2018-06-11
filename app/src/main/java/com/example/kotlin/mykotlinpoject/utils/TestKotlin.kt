package com.example.kotlin.mykotlinpoject.utils

import android.util.Log

class KotlinUtils {
    fun testKotlin() {
        /**
         *  显示转换
         */
        val x: Byte = 1
        val y: Int = x.toInt()
        Log.d("TAG", "x-->$x,y-->$y")

        /**
         * shl(bits) – 有符号左移 (Java 的 <<)
         * shr(bits) – 有符号右移 (Java 的 >>)
         * ushr(bits) – 无符号右移 (Java 的 >>>)
         * and(bits) – 位与
         * or(bits) – 位或
         * xor(bits) – 位异或
         * inv() – 位非
         */
        val a = 1 shl 2
        val b = 1 shr 2
        val c = 1 ushr 2
        val d = 1 and 2
        val e = 1 or 2
        val f = 1 xor 2
        val g = 1.inv()
        Log.d("TAG", "a-->$a,b-->$b,c-->$c,d-->$d,e-->$e,f-->$f,g-->$g")

        /**
         * 三个等号 === 表示比较对象地址
         * 两个 == 表示比较两个值大小
         */
        val a1: Int = 1000
        val b1: Int = 100
        Log.d("TAG", "" + (a1 === a1))
        //装箱创建两个不同对象
        val boxedA: Int? = a1
        val boxedB: Int? = b1
        val anotherBoxedA: Int? = a1
        val anotherBoxedB: Int? = b1
        Log.d("TAG", "boxedA == anotherBoxedA:" + (boxedA == anotherBoxedA) + ",boxedA === anotherBoxedA:" + (boxedA === anotherBoxedA))
        Log.d("TAG", "boxedB == anotherBoxedB:" + (boxedB == anotherBoxedB) + ",boxedB === anotherBoxedB:" + (boxedB === anotherBoxedB))

        /**
         * 数组创建的两种方式：arrayOf()函数和工厂函数
         */
        val arrayA = arrayOf(1, 2, 3)
        val arrayB = Array(3, { i -> (i * 2) })
    }

    /**
     * 如果类有主构造函数，每个次构造函数都要，或直接或间接通过另一个次构造函数代理主构造函数。在同一个类中代理另一个构造函数使用 this 关键字
     */
    class Person(val name: String) {
        constructor (name: String, age: Int) : this(name) {
            // 初始化...
        }
    }

    class Outer {
        private val bar: Int = 1
        var v = "成员属性"

        /**嵌套内部类**/
        inner class Inner {
            fun foo() = bar  // 访问外部类成员
            fun innerTest() {
                var o = this@Outer //获取外部类的成员变量
                println("内部类可以引用外部类的成员，例如：" + o.v)
            }
        }
    }

    /**
     * 继承：
     * 子类有主构造函数， 则基类必须在主构造函数中立即初始化
     *
     */
    open class Customer(name: String) {     //基类
        /**次级构造函数**/
        constructor(name: String, age: Int) : this(name) {
            //初始化
            println("-------基类次级构造函数---------")
        }
    }

    class Student(name: String, age: Int, var no: String, var score: Int) : Customer(name, age) {}
    class Student2 : Customer {
        constructor(name: String) : super(name) {}
        constructor(name: String, age: Int, no: String, score: Int) : super(name, age) {}
    }

    /**
     * 属性重写
     */
    interface Foo {
        val count: Int
    }

    class Bar1(override val count: Int) : Foo

    class Bar2 : Foo {
        override var count: Int = 0
    }

    /**
     * 泛型
     */
    class Box<T>(t: T)

    fun <T> boxInt(value: T) {}

    /**
     * 泛型约束：对泛型的的类型上限进行约束
     */
    fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
            where T : CharSequence,
                  T : Comparable<T> {
        return list.filter { it > threshold }.map { it.toString() }
    }

    /**
     * out 使得一个类型参数协变，协变类型参数只能用作输出，可以作为返回值类型但是无法作为入参的类型
      */
    class RunoobOut<out A>(val a: A) {
        fun foo(): A {
            return a
        }
    }

    /**
     * in 使得一个类型参数逆变，逆变类型参数只能用作输入，可以作为入参的类型但是无法作为返回值的类型
     */
    class RunoobIn<in A>(a: A) {
        fun foo(a: A) {
        }
    }

    /**
     * 类型投影
     */
    fun copy(from: Array<out Any>, to: Array<Any>) {
        assert(from.size == to.size)
        for (i in from.indices)
            to[i] = from[i]
    }

    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "110" }
    fun test() {
        copy(ints, any)
    }

    enum class EnumClass(value: String){}

    /**
     * 使用枚举常量
     */
    enum class Direction {
        NORTH, SOUTH, WEST, EAST
    }

    enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
    }

    enum class ProtocolState {
        WAITING {
            override fun signal() = TALKING
        },
        TALKING {
            override fun signal() = WAITING
        };
        abstract fun signal(): ProtocolState
    }

    /**
     * 星号操作符将可变数量参数以命名形式传入
     */
    fun foo(vararg string: String) {}

    fun testFoo(){
        foo(string = *arrayOf("a", "b", "c"))
    }

    /**
     * 中缀表示法
     */
    infix fun Int.Q(x: Int): Int { return 0 }
    fun testInfix() { 1 Q  2 }

    /**
     * 尾递归函数
     */
    tailrec fun findFixPoint(x: Double = 1.0): Double
        = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))

    private fun findFixPoint(): Double {
        var x = 1.0
        while (true) {
            val y = Math.cos(x)
            if (x == y) return x
            x = y
        }
    }
}

/**
 * 扩展函数
 */
fun KotlinUtils.kz(){
    Log.d("TAG", "KZ")
}
