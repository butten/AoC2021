package days

import day9.Day9
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day9Test {
    private val day9: Day9 = Day9()

    @Test
    fun partOne() {
        assertEquals(day9.runPartOne(), 15)
    }

    @Test
    fun partTwo() {
        assertEquals(day9.runPartTwo(), 1134)
    }
}