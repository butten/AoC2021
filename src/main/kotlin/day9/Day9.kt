package day9

import util.*
import java.io.File

const val wall = 9

class Day9 : Day<Matrix>(9, "Smoke Basin") {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day9().runPartOne()
            Day9().runPartTwo()
        }
    }

    override fun parse(file: File): Matrix {
        return file.readLines()
            .map { line -> line.map { Character.getNumericValue(it) } }
            .toMatrix()
    }

    override fun partOne(): Any {
        val lowPoints = findLowPoints(data)
        return lowPoints.sumOf { data[it.first][it.second] + 1 }
    }

    private fun findLowPoints(data: Matrix): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        for (row in data.indices) {
            for (col in data[row].indices) {
                if (data[row][col] < data.up(row, col, wall)
                    && data[row][col] < data.down(row, col, wall)
                    && data[row][col] < data.left(row, col, wall)
                    && data[row][col] < data.right(row, col, wall)
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

internal fun lookAround(origin: Pair<Int, Int>,  map: Matrix): List<Pair<Int, Int>> {
    // return list of coordinates that are bigger than origin (but not equal to 9)
    val result = mutableListOf<Pair<Int, Int>>()
    val valueAtOrigin = map[origin.first][origin.second]

    val valueUp = map.up(origin.first, origin.second, wall)
    if (valueAtOrigin < valueUp && valueUp != 9) {
        result.add(Pair(origin.first - 1, origin.second))
    }

    val valueDown = map.down(origin.first, origin.second, wall)
    if (valueAtOrigin < valueDown && valueDown != 9) {
        result.add(Pair(origin.first + 1, origin.second))
    }

    val valueLeft = map.left(origin.first, origin.second, wall)
    if (valueAtOrigin < valueLeft && valueLeft != 9) {
        result.add(Pair(origin.first, origin.second - 1))
    }

    val valueRight = map.right(origin.first, origin.second, wall)
    if (valueAtOrigin < valueRight && valueRight != 9) {
        result.add(Pair(origin.first, origin.second + 1))
    }

    return result.toList()
}