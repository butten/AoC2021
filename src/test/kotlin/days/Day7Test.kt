package days

import day7.Day7
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day7Test {
    private val day7: Day7 = Day7()

    @Test
    fun partOne() {
        assertEquals(day7.runPartOne(), 37)
    }

    @Test
    fun partTwo() {
        assertEquals(day7.runPartTwo(), 168)
    }
}