package day4

import day4.Board.Companion.BOARD_WIDTH
import util.Day
import java.io.File

class Day4: Day<Bingo>(4, "Giant Squid") {
    override fun parse(file: File): Bingo {
        val lines = file.readLines()
        //first line contains the random numbers from which we draw
        val numbers = lines[0].split(",").map { it.toInt() }

        val boards = lines.asSequence()
            // remove first two lines from the input_day1
            .drop(2)
            // split each number into elements and remove elements who are empty (happens on single digits)
            .map { it.split(" ").filter { nr -> nr != "" } }
            // filter out the empty lines between boards
            .filter { it.isNotEmpty() }
            // convert all numbers from String to Int
            .map { it.map { nr -> nr.toInt() } }
            // take five rows at a time
            .chunked(BOARD_WIDTH)
            // flatten the five rows into a single list of values, and map to Board
            .map { Board(it.flatten()) }
            .toList()
        return Bingo(numbers, boards)
    }

    override fun partOne(): Int {
        data.numbers.forEach { number ->
            data.boards.forEach { board ->
                val index = board.findIndexOfValue(number)
                if (index != -1) {
                    board.mark(index)

                    if (board.isWinner()) {
                        board.calculateScore(number)
                        return board.score
                    }
                }
            }
        }
        error("No winner :(")
    }

    override fun partTwo(): Int {
        data.numbers.forEach { number ->
            // don't play on boards that has already won
            val boardsLeft = data.boards.filter { it.score == -1 }

            boardsLeft.forEach { board ->
                val index = board.findIndexOfValue(number)
                if (index != -1) {
                    board.mark(index)

                    if (board.isWinner()) {
                        board.calculateScore(number)
                    }
                }
            }
            if (boardsLeft.size == 1 && boardsLeft[0].score != -1) return boardsLeft[0].score
        }
        error("No winner :(")
    }
}