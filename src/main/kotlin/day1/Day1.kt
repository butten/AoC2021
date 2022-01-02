package day1

import util.Util
import java.io.File

fun main() {
    val measurements = File("src/main/kotlin/day1/input").readLines().map { line -> line.toInt() }
    Util.log("simple sweep", System.currentTimeMillis(), simpleSweep(measurements))
    Util.log("advanced sweep", System.currentTimeMillis(), advancedSweep(measurements))
}

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