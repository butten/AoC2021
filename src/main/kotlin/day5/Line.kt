package day5

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {

    private val dx = x2 - x1
    private val dy = y2 - y1

    fun isDiagonal(): Boolean = dx == 0 || dy == 0

    fun maxX(): Int = maxOf(x1, x2)
    fun maxY(): Int = maxOf(y1, y2)
    fun minX(): Int = minOf(x1, x2)
    fun minY(): Int = minOf(y1, y2)

    fun points(): List<Pair<Int, Int>> {
        return if (dx == 0) {
            (minY()..maxY()).map { Pair(x1, it) }
        } else if (dy == 0) {
            (minX()..maxX()).map { Pair(it, y1) }
        } else {
            val k = dy / dx
            val m = y1 - (k * x1)
            return (minX()..maxX()).map { Pair(it, it * k + m) }
        }
    }

    override fun toString(): String {
        return "x1: $x1, y1: $y1, x2: $x2, y2: $y2 \n"
    }
}