package day2

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class Day2KtTest {
    private val day2: Day2 = Day2()
    private lateinit var data: Day2.Commands

    @BeforeEach
    fun setUp() {
        val file = File("src/main/kotlin/day2/test")
        data = day2.parseCommands(file)
    }

    @Test
    fun executeSimplePlannedCourse() {
        assertEquals(day2.executeSimplePlannedCourse(data), 150)
    }

    @Test
    fun executeAdvancedPlannedCourse() {
        assertEquals(day2.executeAdvancedPlannedCourse(data), 900)
    }
}