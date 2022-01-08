package day9

import day8.down
import day8.left
import day8.right
import day8.up
import util.Day
import java.io.File

class Day9 : Day<Array<IntArray>>(9, "Smoke Basin") {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day9().runPartOne()
            Day9().runPartTwo()
        }
    }

    override fun parse(file: File): Array<IntArray> {
        return file.readLines()
            .map { line -> line.map { Character.getNumericValue(it) } }
            .map { it.toIntArray() }.toTypedArray()
    }

    private fun Array<IntArray>.print() {
        this.forEach { println(it.contentToString()) }
    }

    override fun partOne(): Any {
        val lowPoints = findLowPoints(data)
        return lowPoints.sumOf { data[it.first][it.second] + 1 }
    }

    private fun findLowPoints(data: Array<IntArray>): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        for (row in data.indices) {
            for (col in data[row].indices) {
                if (data[row][col] < up(row, col, data)
                    && data[row][col] < down(row, col, data)
                    && data[row][col] < left(row, col, data)
                    && data[row][col] < right(row, col, data)
                ) {
                    result.add(Pair(row, col))
                }
            }
        }
        return result.toList()
    }

    override fun partTwo(): Any {
        val lowPoints = findLowPoints(data)
        // two methods for solving the problem has been implemented. Both are about equally fast

        // using method 1
        val results = lowPoints.map { lowPoint -> ExpandBasin1(lowPoint, data).run().size }.sorted()
        //using method 2
        //val results = lowPoints.map { lowPoint -> ExpandBasin2(lowPoint, data).run().size }.sorted()

        return results[results.size - 1] * results[results.size - 2] * results[results.size - 3]
    }
}