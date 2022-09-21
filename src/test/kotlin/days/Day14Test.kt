package days

import day14.Day14
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day14Test {
    private val day14: Day14 = Day14()

    @Test
    fun partOne() {
        assertEquals(day14.runPartOne(), 1588L)
    }

    @Test
    fun partTwo() {
        assertEquals(day14.runPartTwo(), 2188189693529L)
    }
}