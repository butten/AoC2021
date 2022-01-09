package day9

// ExpandBasin1 uses while loop with a stack
class ExpandBasin2(val lowPoint: Pair<Int, Int>, val map: Array<IntArray>) {
    private val result = mutableSetOf<Pair<Int, Int>>()
    private val stack = ArrayDeque<Pair<Int, Int>>()

    fun run(): Set<Pair<Int, Int>> {
        stack.addFirst(lowPoint)
        expandBasin()
        return result
    }

    private fun expandBasin() {
        while (stack.size > 0) {
            val coord = stack.removeFirst()
            result.add(coord)
            lookAround(coord, map).forEach { newCoord -> stack.addFirst(newCoord) }
        }
    }
}