package day2

import util.Util
import util.Timer
import java.io.File

fun main() {
    val timer = Timer()

    val commands = File("src/main/kotlin/day2/input").readLines()
    val instructions = commands.map { it.substringBefore(" ") }
    val args = commands.map { it.substringAfter(" ").toInt() }
    timer.read()
    
    Util.log(
        "execute simple planned course",
        System.currentTimeMillis(),
        executeSimplePlannedCourse(instructions, args).multiply()
    )
    Util.log(
        "execute simple planned course",
        System.currentTimeMillis(),
        executeAdvancedPlannedCourse(instructions, args).multiply()
    )
}

data class Position(var horizontalPosition: Int, var depth: Int, var aim: Int) {
    override fun toString(): String {
        return "horizontal position: $horizontalPosition, depth: $depth"
    }

    fun multiply(): Int {
        return horizontalPosition * depth
    }
}

fun executeSimplePlannedCourse(instructions: List<String>, args: List<Int>): Position {
    val position = Position(0, 0, 0)

    (instructions zip args).forEach {
        when (it.first) {
            "forward" -> position.horizontalPosition += it.second
            "up" -> position.depth -= it.second
            "down" -> position.depth += it.second
            else -> error("forbidden instruction!")
        }
    }

    return position
}

fun executeAdvancedPlannedCourse(instructions: List<String>, args: List<Int>): Position {
    val position = Position(0, 0, 0)

    (instructions zip args).forEach {
        when (it.first) {
            "forward" -> {
                position.horizontalPosition += it.second
                position.depth += position.aim * it.second
            }
            "up" -> position.aim -= it.second
            "down" -> position.aim += it.second
            else -> error("forbidden instruction!")
        }
    }

    return position
}