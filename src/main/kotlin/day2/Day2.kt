package day2

import util.Util
import java.io.File

fun main() {
    val file = File("src/main/kotlin/day2/input")
    val commands = Util.log(
        "parse commands",
        System.currentTimeMillis(),
        parseCommands(file)
    ) as Commands
    Util.log(
        "execute simple planned course",
        System.currentTimeMillis(),
        executeSimplePlannedCourse(commands).multiply()
    )
    Util.log(
        "execute simple planned course",
        System.currentTimeMillis(),
        executeAdvancedPlannedCourse(commands).multiply()
    )
}

data class Commands(val instructions: List<String>, val args: List<Int>)

fun parseCommands(file: File): Commands {
    val commands = file.readLines()
    val instructions = commands.map { it.substringBefore(" ") }
    val args = commands.map { it.substringAfter(" ").toInt() }
    return Commands(instructions, args)
}

fun executeSimplePlannedCourse(commands: Commands): Position {
    val position = Position(0, 0, 0)

    (commands.instructions zip commands.args).forEach {
        when (it.first) {
            "forward" -> position.horizontalPosition += it.second
            "up" -> position.depth -= it.second
            "down" -> position.depth += it.second
            else -> error("forbidden instruction!")
        }
    }

    return position
}

fun executeAdvancedPlannedCourse(commands: Commands): Position {
    val position = Position(0, 0, 0)

    (commands.instructions zip commands.args).forEach {
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