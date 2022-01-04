package day4

import day4.Board.Companion.BOARD_WIDTH
import util.Util
import java.io.File

fun main() {
    val day4 = Day4()
    val file = File("src/main/kotlin/day4/input").readLines()
    val bingo = Util.log(
        "parse bingo",
        System.currentTimeMillis(),
        day4.parseBingo(file)
    ) as Bingo
    Util.log(
        "play bingo with squid",
        System.currentTimeMillis(),
        day4.play(bingo)
    )
    Util.log(
        "let the squid win",
        System.currentTimeMillis(),
        day4.lose(bingo)
    )
}

class Day4 {
    fun parseBingo(bingoData: List<String>): Bingo {
        //first line contains the random numbers from which we draw
        val numbers = bingoData[0].split(",").map { it.toInt() }

        val boards = bingoData.asSequence()
            // remove first two lines from the input
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

    fun play(bingo: Bingo): Int {
        bingo.numbers.forEach { number ->
            bingo.boards.forEach { board ->
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

    fun lose(bingo: Bingo): Int {
        bingo.numbers.forEach { number ->
            // don't play on boards that has already won
            val boardsLeft = bingo.boards.filter { it.score == -1 }

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