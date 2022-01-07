package days

import day8.Day8
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day8Test {
    private val day8: Day8 = Day8()

    @Test
    fun partOne() {
        assertEquals(day8.runPartOne(), 26)
    }

    @Test
    fun partTwo() {
        assertEquals(day8.runPartTwo(), 61229)
    }
}