package util

import day1.Day1
import day2.Day2
import day3.Day3
import day4.Day4
import day5.Day5
import day6.Day6
import day7.Day7

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        // todo: find these in a smarter way
        val days = listOf(Day1(), Day2(), Day3(), Day4(), Day5(), Day6(), Day7())
        days.forEach { day ->
            day.runPartOne()
            day.runPartTwo()
        }
    }
}
