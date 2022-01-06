package day6

import util.Day
import java.io.File

class Day6 : Day<LongArray>(6, "Lanternfish") {
    override fun parse(file: File): LongArray {
        return file.readLines()[0]
            .split(",")
            .map { it.toLong() }
            .toLongArray()
            .countEach(9)
    }

    // counts occurrence of each value in array
    private fun LongArray.countEach(size: Int): LongArray {
        val newArray = LongArray(size)
        this.forEach { newArray[it.toInt()]++ }
        return newArray
    }

    // rotates the array left by d steps
    // taken from: https://stackoverflow.com/a/52892357
    private fun LongArray.rotateLeft(d: Int): LongArray {
        val n = d % this.size  // just in case
        if (n == 0) return this  // no need to shift

        val left = this.copyOfRange(0, n)
        val right = this.copyOfRange(n, this.size)
        System.arraycopy(right, 0, this, 0, right.size)
        System.arraycopy(left, 0, this, right.size, left.size)
        return this
    }

    override fun partOne(): Long = simulate(80)

    override fun partTwo(): Long = simulate(256)

    private fun simulate(days: Int): Long {
        for (i in 1..days) {
            val newFishes = data[0]
            data[0] = 0
            data.rotateLeft(1)
            if (newFishes > 0) {
                data[6] += newFishes
                data[8] += newFishes
            }
        }
        return data.sum()
    }
}