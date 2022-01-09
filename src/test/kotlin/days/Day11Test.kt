package days

import Day11
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day11Test {
    private val day11: Day11 = Day11()

    @Test
    fun partOne() {
        assertEquals(day11.runPartOne(), 1656)
    }

    @Test
    fun partTwo() {
        assertEquals(day11.runPartTwo(), 195)
    }
}