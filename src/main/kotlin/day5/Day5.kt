package day5

import util.Util
import java.io.File

fun main() {
    val day5 = Day5()
    val file = File("src/main/kotlin/day5/test")
    val lines = Util.log(
        "parse lines",
        System.currentTimeMillis(),
        day5.parseLines(file)
    ).filterIsInstance<Line>()
    Util.log(
        "execute simple planned course",
        System.currentTimeMillis(),
        day5.findDangerousCoordinates(lines)
    )
}

class Day5 {
    fun parseLines(file: File): List<Line> {
        return file.readLines()
            .map { row -> row.split(",", " -> ").map { element -> element.toInt() } }
            .map { Line(it[0], it[1], it[2], it[3]) }
    }

    fun findDangerousCoordinates(lines: List<Line>) {
        println("horizontal and vertical lines: \n ${lines.filterDiagLines()}")
    }
}
