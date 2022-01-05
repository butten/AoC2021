package day5

import util.Day
import java.io.File

class Day5 : Day<List<Line>>(5, "Hydrothermal Venture") {
    override fun parse(file: File): List<Line> {
        return file.readLines()
            .map { row -> row.split(",", " -> ").map { element -> element.toInt() } }
            .map { Line(it[0], it[1], it[2], it[3]) }
    }

    override fun partOne(): Int = compute(false)

    override fun partTwo(): Int = compute(true)

    private fun compute(considerDiagonals: Boolean): Int {
        // map[y][x]
        val map = Array(data.maxOf { it.maxY() + 1 }) { Array(data.maxOf { it.maxX() + 1 }) { 0 } }

        // place line points on map
        data.filter { if (considerDiagonals) true else it.isDiagonal() }
            .forEach { line ->
                line.points().forEach { map[it.second][it.first] += 1 }
            }

        // find coords with 2 or more points
        return map.flatten().count { it >= 2 }
    }
}