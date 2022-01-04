package day5

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    override fun toString(): String {
        return "x1: $x1, y1: $y1, x2: $x2, y2: $y2 \n"
    }
}