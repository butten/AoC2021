package day4

import day4.Board.Companion.BOARD_WIDTH
import util.Util
import java.io.File

fun main() {
    val bingoData = File("src/main/kotlin/day4/input").readLines()
    val bingo = parseBingo(bingoData)
    Util.log(
        "play bingo with squid",
        System.currentTimeMillis(),
        play(bingo)
    )
    Util.log(
        "let the squid win",
        System.currentTimeMillis(),
        lose(bingo)
    )
}

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

data class Bingo(val numbers: List<Int>, val boards: List<Board>)

data class Board(val values: List<Int>) {
    companion object {
        const val BOARD_WIDTH = 5
    }

    private val marks = MutableList(BOARD_WIDTH * BOARD_WIDTH) { 0 }
    var score = -1

    fun getX(index: Int): Int = index % BOARD_WIDTH
    fun getY(index: Int): Int = index / BOARD_WIDTH

    fun findIndexOfValue(number: Int): Int {
        return values.indexOfFirst { it == number }
    }

    fun getMarksRow(row: Int): List<Int> = marks.slice(row * BOARD_WIDTH until row * BOARD_WIDTH + BOARD_WIDTH)
    fun getMarksCol(col: Int): List<Int> = marks.slice((0 + col) until marks.size step BOARD_WIDTH)

    fun mark(index: Int) {
        marks[index] = 1
    }

    fun isWinner(): Boolean {
        val row = (0 until BOARD_WIDTH)                     // for each row of the board
            .map { getMarksRow(it).sum() == BOARD_WIDTH }   // sum of a win row is 5 (1+1+1+1+1)
            .find { it } ?: false                           // if any row is 5 -> win!

        val col = (0 until BOARD_WIDTH)                     // for each column of the board
            .map { getMarksCol(it).sum() == BOARD_WIDTH }   // sum of a win col is 5 (1+1+1+1+1)
            .find { it } ?: false                           // if any col is 5 -> win!
        return row or col
    }

    fun calculateScore(number: Int) {
        score = number * values.asSequence()
            .filterIndexed { index, _ -> marks[index] == 0 }
            .sumBy { it }
    }
}