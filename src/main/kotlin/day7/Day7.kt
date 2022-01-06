package day7

import util.Day
import java.io.File
import kotlin.math.abs

class Day7 : Day<IntArray>(7, "The Treachery of Whales") {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day7().runPartOne()
            Day7().runPartTwo()
        }
    }

    override fun parse(file: File): IntArray {
        return file.readLines()[0].split(",").map { it.toInt() }.toIntArray()
    }

    override fun partOne(): Any {
        return (0..data.maxOf { it })   // for every possible alignment position
            .map { alignPos -> data.sumOf { abs(it - alignPos) } }  // compute total fuel cost to that pos
            .minOf { it }   // take the smallest fuel consumption
    }


    override fun partTwo(): Any {
        return (2..data.maxOf { it })   // for every possible alignment position
            .map { alignPos -> data.sumOf { abs(it - alignPos).sumSeries() } } // compute total fuel cost to that pos
            .minOf { it }   // take the smallest fuel consumption
    }

    private fun Int.sumSeries(): Int = this * (this + 1) / 2
}