package day9

import day8.lookAround

// ExpandBasin1 uses recursion
class ExpandBasin1(val lowPoint: Pair<Int, Int>, val map: Array<IntArray>) {
    private val result = mutableSetOf<Pair<Int, Int>>()

    fun run(): Set<Pair<Int, Int>> {
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.addFirst(lowPoint)
        expandBasin(stack)
        return result
    }

    private fun expandBasin(stack: ArrayDeque<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val top = stack.removeFirstOrNull() ?: return result.toList()   // if stack is empty we are done
        result.add(top)
        lookAround(top, map).forEach { newCoord -> stack.addFirst(newCoord)}  // find new coordinates and add to stack
        return expandBasin(stack)
    }
}