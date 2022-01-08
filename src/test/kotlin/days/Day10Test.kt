package days

import day10.Day10
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10Test {
    private val day10: Day10 = Day10()

    @Test
    fun partOne() {
        assertEquals(day10.runPartOne(), 26397)
    }

    @Test
    fun partTwo() {
        assertEquals(day10.runPartTwo(), 288957L)
    }
}