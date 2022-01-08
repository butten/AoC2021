package day8

private const val wall = 9

internal fun up(row: Int, col: Int, map: Array<IntArray>): Int {
    return try {
        map[row - 1][col]
    } catch (err: ArrayIndexOutOfBoundsException) {
        wall
    }
}

internal fun down(row: Int, col: Int, map: Array<IntArray>): Int {
    return try {
        map[row + 1][col]
    } catch (err: ArrayIndexOutOfBoundsException) {
        wall
    }
}

internal fun left(row: Int, col: Int, map: Array<IntArray>): Int {
    return try {
        map[row][col - 1]
    } catch (err: ArrayIndexOutOfBoundsException) {
        wall
    }
}

internal fun right(row: Int, col: Int, map: Array<IntArray>): Int {
    return try {
        map[row][col + 1]
    } catch (err: ArrayIndexOutOfBoundsException) {
        wall
    }
}

internal fun lookAround(origin: Pair<Int, Int>,  map: Array<IntArray>): List<Pair<Int, Int>> {
    // return list of coordinates that are bigger than origin (but not equal to 9)
    val result = mutableListOf<Pair<Int, Int>>()
    val valueAtOrigin = map[origin.first][origin.second]

    val valueUp = up(origin.first, origin.second, map)
    if (valueAtOrigin < valueUp && valueUp != 9) {
        result.add(Pair(origin.first - 1, origin.second))
    }

    val valueDown = down(origin.first, origin.second, map)
    if (valueAtOrigin < valueDown && valueDown != 9) {
        result.add(Pair(origin.first + 1, origin.second))
    }

    val valueLeft = left(origin.first, origin.second, map)
    if (valueAtOrigin < valueLeft && valueLeft != 9) {
        result.add(Pair(origin.first, origin.second - 1))
    }

    val valueRight = right(origin.first, origin.second, map)
    if (valueAtOrigin < valueRight && valueRight != 9) {
        result.add(Pair(origin.first, origin.second + 1))
    }

    return result.toList()
}
