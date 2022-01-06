package util

import java.io.File

abstract class Day<T>(private val day: Int, private val title: String) {

    private val file = File(javaClass.classLoader.getResource("input_day$day")!!.toURI())
    protected val data by lazy { parse(file) }  // init when first called

    protected abstract fun parse(file: File): T
    protected abstract fun partOne(): Any
    protected abstract fun partTwo(): Any

    fun runPartOne(): Any {
        val startTime = System.currentTimeMillis()
        val result = partOne()
        val execTime = System.currentTimeMillis() - startTime
        println("Day $day $title part1:      $result.       Time: $execTime millis")
        return result
    }

    fun runPartTwo(): Any {
        val startTime = System.currentTimeMillis()
        val result = partTwo()
        val execTime = System.currentTimeMillis() - startTime
        println("Day $day $title part2:      $result.       Time: $execTime millis")
        return result
    }
}