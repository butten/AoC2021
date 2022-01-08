package day10

import util.Day
import java.io.File

class Day10 : Day<List<String>>(10, "Syntax Scoring") {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day10().runPartOne()
            Day10().runPartTwo()
        }
    }

    override fun parse(file: File): List<String> {
        return file.readLines()
    }

    class SyntaxLine(private val s: String) {
        companion object {
            val open = listOf('(', '[', '{', '<')
            val close = listOf(')', ']', '}', '>')
            val corruptPoints = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
            val autocompleteScore = mapOf(')' to 1L, ']' to 2L, '}' to 3L, '>' to 4L)
        }

        @Suppress("MemberVisibilityCanBePrivate")
        // returns list of chars needed for completing the line
        fun autocomplete(): List<Char> = eval(s).map { close[open.indexOf(it)] }
        fun autocompleteScore(): Long = autocomplete()
            .map { autocompleteScore[it] ?: 0L }
            .fold(0L) { sum, element -> sum * 5L + element }

        // returns null if not corrupt
        fun findCorrupt(): Char? = eval(s).asReversed().intersect(close).firstOrNull()
        fun corruptScore(): Int = corruptPoints[findCorrupt()] ?: 0

        private fun eval(s: String): List<Char> {
            val stack = ArrayDeque<Char>()

            s.forEach { c ->
                stack.addFirst(c)
                for (type in open.indices) {
                    if (!stack.isEmpty() && stack[0] == close[type] && stack[1] == open[type]) {
                        stack.removeFirst()
                        stack.removeFirst()
                    }
                }
            }
            return stack.toList()
        }
    }

    override fun partOne(): Any {
        return data.map { SyntaxLine(it) }
            .map { it.corruptScore() }.sumOf { it }
    }

    override fun partTwo(): Any {
        val resultList = data.map { SyntaxLine(it) }
            .filter { it.findCorrupt() == null }
            .map { it.autocompleteScore() }
            .sorted()

        return resultList[resultList.size / 2]
    }
}