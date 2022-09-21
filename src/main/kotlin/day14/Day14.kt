package day14

import util.Day
import java.io.File
import kotlin.collections.HashMap

class Day14 : Day<Day14.Instruction>(14, "Extended Polymerization") {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day14().runPartOne()
            Day14().runPartTwo()
        }
    }

    override fun parse(file: File): Instruction {

        val polymerTemplate = file.readLines()[0];

        val pairInsertions = file.readLines()
            .filter { it.contains("->") }
            .map { it.split(" -> ") }
            //.map { Pair(it[0], it[1]) }
            .map { Pair(it[0], Pair(it[0][0] + it[1], it[1] + it[0][1])) }

        return Instruction(polymerTemplate, pairInsertions)
    }

    data class Instruction(
        var polymerTemplate: String,
        var pairInsertions: List<Pair<String, Pair<String, String>>>
    ) {
        override fun toString(): String {
            return polymerTemplate + "\n" + pairInsertions
        }
    }

    private fun process(count: MutableMap<String, Long>): MutableMap<String, Long> {
        val newCount: MutableMap<String, Long> = HashMap()
        count.forEach { (k, c) ->
            //println("Doing $k ($c)")
            val instruction = data.pairInsertions.find { it.first == k } ?: throw Exception("Did not find instruction")
            //println("Found instruction: $instruction")
            newCount[instruction.second.first] = newCount.getOrElse(instruction.second.first) { 0 } + c
            newCount[instruction.second.second] = newCount.getOrElse(instruction.second.second) { 0 } + c
            //println("New count: $newCount")
        }
        return newCount
    }

    private fun countElements(count: MutableMap<String, Long>): Map<Char, Long> {
        val elements = count.keys.joinToString("").toSet()
        val elementCount: MutableMap<Char, Long> = HashMap()

        // pairs like BB, CC etc counts twice
        count.forEach { k, v -> if (k[0] == k[1]) count[k] = v * 2 }

        // every letter except the first and last is part of exactly two pairs, so just adding these and dividing by two fixes it
        elements.forEach { element ->
            val keys = count.keys.filter { it.contains(element) }
            elementCount[element] = keys.sumOf { count[it] ?: 0 } / 2
        }

        // first and last element needs to be added
        elementCount[data.polymerTemplate[0]] = (elementCount[data.polymerTemplate[0]] ?: 0) + 1
        elementCount[data.polymerTemplate[data.polymerTemplate.length - 1]] =
            (elementCount[data.polymerTemplate[data.polymerTemplate.length - 1]] ?: 0) + 1

        return elementCount
    }

    override fun partOne(): Any {
        var count: MutableMap<String, Long> = HashMap()
        data.polymerTemplate
            .windowed(2, 1)
            .forEach { count[it] = count.getOrElse(it) { 0 } + 1 }

        for (i in 1..10) {
            count = process(count)
        }
        val elementCount = countElements(count)
        return (elementCount.values.maxOrNull() ?: 0) - (elementCount.values.minOrNull() ?: 0)
    }

    override fun partTwo(): Any {
        var count: MutableMap<String, Long> = HashMap()
        data.polymerTemplate
            .windowed(2, 1)
            .forEach { count[it] = count.getOrElse(it) { 0 } + 1 }
        //println("Init count: $count")

        for (i in 1..40) {
            count = process(count)
        }
        //println("final count: $count")

        val elementCount = countElements(count)
        //println(elementCount)

        return (elementCount.values.maxOrNull() ?: 0) - (elementCount.values.minOrNull() ?: 0)
    }
}