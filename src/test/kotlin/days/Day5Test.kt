package days

import day5.Day5
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5Test {
    private val day5: Day5 = Day5()

    @Test
    fun partOne() {
        assertEquals(5, day5.runPartOne())
    }

    @Test
    fun partTwo() {
        assertEquals(12, day5.runPartTwo())
    }
}