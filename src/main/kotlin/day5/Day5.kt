package day5

import java.io.File

fun main() {
    val linesData = File("src/main/kotlin/day5/test").readLines()
    val lines = parseLines(linesData)
    findDangerousCoordinates(lines)
}

fun findDangerousCoordinates(lines: Unit) {

}

fun parseLines(linesData: List<String>) {
    linesData.forEach { line ->
        line.split(",", " -> ")                 // split each line using delimiters
            .map { it.toInt() }                           // cast each string value to Int
            .chunked(4)                              // bunch the values into groups of four
            .map { Line(it[0], it[1], it[2], it[3]) }     // map to Line objects
    }
}

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)