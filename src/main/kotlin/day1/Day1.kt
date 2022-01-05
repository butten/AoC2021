package day1

import util.Day
import java.io.File

class Day1 : Day<List<Int>>(1, "Sonar Sweep") {
    override fun parse(file: File): List<Int> = file.readLines().map { line -> line.toInt() }

    override fun partOne(): Int {
        return data.zipWithNext().count { it.second > it.first }
    }

    override fun partTwo(): Int {
        return data.asSequence()                // asSequence to improve performance
            .windowed(3)                   // create windows of 3 measurements
            .map { it[0] + it[1] + it[2] }      // sum each window
            .zipWithNext()                      // pair with next sum
            .filter { it.second > it.first }
            .count()
    }
}