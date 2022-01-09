import util.*
import java.io.File

class Day11 : Day<Matrix>(11, "") {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day11().runPartOne()
            Day11().runPartTwo()
        }
    }

    override fun parse(file: File): Matrix {
        return file.readLines()
            .map { line -> line.map { Character.getNumericValue(it) } }
            .toMatrix()
    }

    override fun partOne(): Any {
        return simulate(100).first
    }

    private fun simulate(steps: Int): Pair<Int, Int> {
        var nrOfFlashes = 0
        val stepsWhenAllFlash = mutableListOf<Int>()

        // todo: fix debug logging
        //println("Before any steps:")
        //data.print(0)
        //println()

        for (i in 1..steps) {
            // step 1 - increase energy level of each octopus by 1
            for (row in data.indices) {
                for (col in data[row].indices) {
                    data[row][col]++
                }
            }

            // step 2 - while there are values equal to 10
            while (data.flatMap { it.asIterable() }.any { it > 9 }) {

                // find all octopuses to flash
                val flashes = mutableListOf<Pair<Int, Int>>()
                for (row in data.indices) {
                    for (col in data[row].indices) {
                        if (data[row][col] > 9) {
                            flashes.add(Pair(row, col))
                            data[row][col] = Int.MIN_VALUE
                        }
                    }
                }

                // flash em
                flashes.forEach {
                    data.increaseAround(it.first, it.second)
                    nrOfFlashes++
                }
            }

            // step 3 - set all energy levels > 9 to 0
            for (row in data.indices) {
                for (col in data[row].indices) {
                    if (data[row][col] < 0) data[row][col] = 0
                }
            }

            // todo: fix debug logging
            //println("After step $i ($nrOfFlashes flashes):")
            //data.print(0)
            //println()

            //sanity check
            for (row in data.indices) {
                for (col in data[row].indices) {
                    if (data[row][col] > 9) error("value bigger than 9 found at $row, $col (${data[row][col]})")
                }
            }

            if (data.allEqual(0)) {
                stepsWhenAllFlash.add(i)
            }
        }

        // todo: fix debug logging
        //println("steps when all flash: $stepsWhenAllFlash")
        return Pair(nrOfFlashes, stepsWhenAllFlash.getOrElse(0) { -1 })
    }

    override fun partTwo(): Any {
        return simulate(1000).second
    }
}