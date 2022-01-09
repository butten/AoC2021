package util

// Used by Days who can be represented as 2d maps
typealias Matrix = Array<IntArray>

const val bright = "\u001b[97m"
const val reset = "\u001b[0m"

fun List<List<Int>>.toMatrix(): Matrix {
    return this.map { it.toIntArray() }.toTypedArray()
}

fun Matrix.print(highlight: Int = -1) {
    this.forEach { row ->
        row.forEach { if (it == highlight) print(bright + it + reset) else kotlin.io.print(it) }
        println()
    }
}

fun Matrix.forEach() {}

fun Matrix.cols(): Int = this[0].size
fun Matrix.rows(): Int = this.size

fun Matrix.increaseAround(row: Int, col: Int) {
    if (row != 0) this[row - 1][col]++
    if (row != rows() - 1) this[row + 1][col]++
    if (col != 0) this[row][col - 1]++
    if (col != cols() - 1) this[row][col + 1]++
    if (row != 0 && col != 0) this[row - 1][col - 1]++
    if (row != 0 && col != cols() - 1) this[row - 1][col + 1]++
    if (row < rows() - 1 && col != 0) this[row + 1][col - 1]++
    if (row < rows() - 1 && col != cols() - 1) this[row + 1][col + 1]++
}

fun Matrix.allEqual(value: Int): Boolean {
    for (row in this.indices) {
        for (col in this[row].indices) {
             if (this[row][col] != value) return false
        }
    }
    return true
}

fun Matrix.up(row: Int, col: Int, ifOutside: Int): Int {
    return try {
        this[row - 1][col]
    } catch (err: ArrayIndexOutOfBoundsException) {
        ifOutside
    }
}

fun Matrix.down(row: Int, col: Int, ifOutside: Int): Int {
    return try {
        this[row + 1][col]
    } catch (err: ArrayIndexOutOfBoundsException) {
        ifOutside
    }
}

fun Matrix.left(row: Int, col: Int, ifOutside: Int): Int {
    return try {
        this[row][col - 1]
    } catch (err: ArrayIndexOutOfBoundsException) {
        ifOutside
    }
}

fun Matrix.right(row: Int, col: Int, ifOutside: Int): Int {
    return try {
        this[row][col + 1]
    } catch (err: ArrayIndexOutOfBoundsException) {
        ifOutside
    }
}

fun Matrix.upLeft(row: Int, col: Int, ifOutside: Int): Int {
    return try {
        this[row - 1][col - 1]
    } catch (err: ArrayIndexOutOfBoundsException) {
        ifOutside
    }
}

fun Matrix.upRight(row: Int, col: Int, ifOutside: Int): Int {
    return try {
        this[row - 1][col + 1]
    } catch (err: ArrayIndexOutOfBoundsException) {
        ifOutside
    }
}

fun Matrix.downLeft(row: Int, col: Int, ifOutside: Int): Int {
    return try {
        this[row + 1][col - 1]
    } catch (err: ArrayIndexOutOfBoundsException) {
        ifOutside
    }
}

fun Matrix.downRight(row: Int, col: Int, ifOutside: Int): Int {
    return try {
        this[row + 1][col + 1]
    } catch (err: ArrayIndexOutOfBoundsException) {
        ifOutside
    }
}