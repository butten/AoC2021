package day12

import util.Day
import java.io.File

class Day12 : Day<Map<String, Set<String>>>(12, "Passage Pathing") {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day12().runPartOne()
            Day12().runPartTwo()
        }

        // return false if any letter is uppercase
        fun String.isStringLowerCase(): Boolean {
            this.forEach { if (it.isUpperCase()) return false }
            return true
        }
    }

    override fun parse(file: File): Map<String, Set<String>> {
        val graph = mutableMapOf<String, MutableSet<String>>()
        file.readLines()
            .map { it.split("-") }
            .forEach {
                graph.getOrPut(it[0]) { mutableSetOf() }.add(it[1])
                graph.getOrPut(it[1]) { mutableSetOf() }.add(it[0])
            }
        // remove start from lists of children to make start edges one-directional
        graph.values.forEach { it.remove("start") }
        return graph
    }

    override fun partOne(): Any {
        return buildTree("start", "end", false)
            .findNodesWithName("end", mutableListOf())
            .size
    }

    override fun partTwo(): Any {
        val tree = buildTree("start", "end", true)
        /*
        todo: fix debug logging
        tree.findNodesWithName("end", mutableListOf()).forEach {
            it.printPath()
        }
         */
        return tree.findNodesWithName("end", mutableListOf()).size
    }

    @Suppress("SameParameterValue")
    private fun buildTree(root: String, goal: String, visitSingleSmallCaveTwice: Boolean): Node {
        val tree = Node(root, 0, listOf())
        val stack = ArrayDeque<Node>()
        stack.addFirst(tree)

        while (stack.isNotEmpty()) {
            val v = stack.removeFirst()

            if (allowedToVisit(v, visitSingleSmallCaveTwice) && !v.explored.contains(goal)) {
                data[v.name]!!.forEach { w ->
                    //println("At ${v.name} (lvl ${v.level}): adding node $w")

                    val childNode = Node(w, v.level + 1, v.explored + v.name)
                    if (childNode.name != goal) stack.addFirst(childNode)
                    v.children.add(childNode)
                }
            }
        }
        return tree
    }

    // used for part 2
    private fun allowedToVisit(n: Node, visitSingleSmallCaveTwice: Boolean): Boolean {
        // if all letters are uppercase -> big cave
        if (!n.name.isStringLowerCase()) return true

        // if it doesn't exist in the explored list, we can explore it for sure
        if (!n.exploredSmallCaves().contains(n.name)) return true

        // if we can only visit small caves once, we stop here
        if (!visitSingleSmallCaveTwice) return false

        // if we have only explored small caves either once or never, we can afford to visit one small cave twice
        val countOfEach = n.exploredSmallCaves().groupingBy { it }.eachCount().values
        return countOfEach.all { it == 1 }
    }
}