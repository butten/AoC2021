package day2

import util.Day
import util.Util
import java.io.File

class Day2: Day<Commands>(2, "Dive!") {
    override fun parse(file: File): Commands {
        val commands = file.readLines()
        val instructions = commands.map { it.substringBefore(" ") }
        val args = commands.map { it.substringAfter(" ").toInt() }
        return Commands(instructions, args)
    }

    override fun partOne(): Int {
        val position = Position(0, 0, 0)

        (data.instructions zip data.args).forEach {
            when (it.first) {
                "forward" -> position.horizontalPosition += it.second
                "up" -> position.depth -= it.second
                "down" -> position.depth += it.second
                else -> error("forbidden instruction!")
            }
        }

        return position.multiply()
    }

    override fun partTwo(): Int {
        val position = Position(0, 0, 0)

        (data.instructions zip data.args).forEach {
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

        return position.multiply()
    }
}