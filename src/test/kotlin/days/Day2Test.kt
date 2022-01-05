package days

import day2.Day2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2Test {
    private val day2: Day2 = Day2()

    @Test
    fun partOne() {
        assertEquals(day2.runPartOne(), 150)
    }

    @Test
    fun partTwo() {
        assertEquals(day2.runPartTwo(), 900)
    }
}