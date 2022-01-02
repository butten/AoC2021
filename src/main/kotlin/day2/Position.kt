package day2

data class Position(var horizontalPosition: Int, var depth: Int, var aim: Int) {
    override fun toString(): String {
        return "horizontal position: $horizontalPosition, depth: $depth"
    }

    fun multiply(): Int {
        return horizontalPosition * depth
    }
}