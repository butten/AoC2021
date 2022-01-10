package days

import day13.Day13
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day13Test {
    private val day13: Day13 = Day13()

    @Test
    fun partOne() {
        assertEquals(day13.runPartOne(), 17)
    }

    // Can't really test as the answer is found in the printout
    @Test
    fun partTwo() {
    }
}