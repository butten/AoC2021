package day1

import util.Util
import java.io.File

fun main() {
    val day1 = Day1()
    val file = File("src/main/kotlin/day1/input")
    val measurements: List<Int> = Util.log(
        "parse measurements",
        System.currentTimeMillis(),
        day1.parseMeasurements(file)
    ).filterIsInstance<Int>()
    Util.log(
        "simple sweep",
        System.currentTimeMillis(),
        day1.simpleSweep(measurements)
    )
    Util.log(
        "advanced sweep",
        System.currentTimeMillis(),
        day1.advancedSweep(measurements)
    )
}

class Day1 {
    fun parseMeasurements(file: File): List<Int> = file.readLines().map { line -> line.toInt() }

    fun simpleSweep(measurements: List<Int>): Int {
        return measurements.zipWithNext()
            .filter { it.second > it.first }
            .count()
    }

    fun advancedSweep(measurements: List<Int>): Int {
        return measurements.asSequence()        // asSequence to improve performance
            .windowed(3)                    // create windows of 3 measurements
            .map { it[0] + it[1] + it[2] }      // sum each window
            .zipWithNext()                      // pair with next sum
            .filter { it.second > it.first }
            .count()
    }
}