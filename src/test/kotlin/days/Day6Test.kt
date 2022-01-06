package days

import day6.Day6
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day6Test {
    private val day6: Day6 = Day6()

    @Test
    fun partOne() {
        assertEquals(day6.runPartOne(), 5934L)
    }

    @Test
    fun partTwo() {
        assertEquals(day6.runPartTwo(), 26984457539L)
    }
}