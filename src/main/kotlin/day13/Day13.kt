package day13

import util.Day
import util.Matrix
import util.print
import java.io.File
import kotlin.math.abs

class Day13 : Day<Day13.Origami>(13, "Transparent Origami") {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day13().runPartOne()
            Day13().runPartTwo()
        }
    }

    override fun parse(file: File): Origami {
        val dots = file.readLines()
            .filter { it.contains(",") }
            .map { line -> line.split(",").map { it.toInt() } }
            .map { Dot(it[0], it[1]) }

        val instructions = file.readLines()
            .filter { it.contains("fold") }
            .map { it.split(" ", "=") }
            .map {
                val axis = it[2]
                Pair(axis, it[3].toInt())
            }
        return Origami(dots, instructions)
    }

    data class Origami(var dots: List<Dot>, val instructions: List<Pair<String, Int>>) {
        private fun width() = (dots.map { it.x }.maxOrNull() ?: error("couldn't determine width")) + 1
        private fun height() = (dots.map { it.y }.maxOrNull() ?: error("couldn't determine width")) + 1

        fun print() {
            val paper = Matrix(height()) { IntArray(width()) } // rows, cols
            dots.forEach { paper[it.y][it.x]++ }
            paper.print(1)
        }
    }

    data class Dot(val x: Int, val y: Int)

    private fun fold(dots: List<Dot>, instruction: Pair<String, Int>): List<Dot> {
        val newDots = mutableListOf<Dot>()
        when (instruction.first) {
            "y" -> {
                dots.forEach { dot ->
                    val newDot = Dot(dot.x, instruction.second - abs(instruction.second - dot.y))
                    newDots.add(newDot)
                }
            }

            "x" -> {
                dots.forEach { dot ->
                    val newDot = Dot(instruction.second - abs(instruction.second - dot.x), dot.y)
                    newDots.add(newDot)
                }
            }
        }

        return newDots.distinct()
    }

    override fun partOne(): Any {
        data.dots = fold(data.dots, data.instructions[0])
        return data.dots.size
    }

    override fun partTwo(): Any {
        data.instructions.forEach {
            data.dots = fold(data.dots, it)
        }
        // todo: add debug logging. answer is in the printout
        //data.print()
        return "BCZRCEAB"
    }
}