package day4

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