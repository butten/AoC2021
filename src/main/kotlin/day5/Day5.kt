package day5

import util.Day
import java.io.File

class Day5: Day<List<Line>>(5, "Hydrothermal Venture") {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day5().runPartOne()
        }
    }

    override fun parse(file: File): List<Line> {
        return file.readLines()
            .map { row -> row.split(",", " -> ").map { element -> element.toInt() } }
            .map { Line(it[0], it[1], it[2], it[3]) }
    }

    override fun partOne(): Int {
        println("horizontal and vertical lines: \n ${data.filterDiagLines()}")
        return -1
    }

    override fun partTwo(): Int {
        return -1
    }
}
