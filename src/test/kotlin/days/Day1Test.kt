package days

import day1.Day1
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1Test {
    private val day1: Day1 = Day1()

    @Test
    fun partOne() {
        assertEquals(7, day1.runPartOne())

    }

    @Test
    fun partTwo() {
        assertEquals(5, day1.runPartTwo())
    }
}