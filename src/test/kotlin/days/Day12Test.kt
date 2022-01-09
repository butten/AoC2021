package days

import day12.Day12
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day12Test {
    private val day12: Day12 = Day12()

    @Test
    fun partOne() {
        assertEquals(day12.runPartOne(), 19)
    }

    @Test
    fun partTwo() {
        assertEquals(day12.runPartTwo(), 103)
    }
}