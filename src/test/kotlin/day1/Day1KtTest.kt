package day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

internal class Day1KtTest {
    private val day1: Day1 = Day1()
    private lateinit var data: List<Int>

    @BeforeEach
    fun parse() {
        val file = File("src/main/kotlin/day1/test")
        data = day1.parseMeasurements(file)
    }

    @Test
    fun simpleSweep() {
        assertEquals(7, day1.simpleSweep(data))

    }

    @Test
    fun advancedSweep() {
        assertEquals(5, day1.advancedSweep(data))
    }
}