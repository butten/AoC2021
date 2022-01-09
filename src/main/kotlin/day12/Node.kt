package day12

import day12.Day12.Companion.isStringLowerCase

data class Node(val name: String, val level: Int, val explored: List<String>) {
    val children: MutableList<Node> = mutableListOf()

    fun findNodesWithName(name: String, result: MutableList<Node>): MutableList<Node> {
        if (this.name == name) result.add(this)
        children.forEach { it.findNodesWithName(name, result) }
        return result
    }

    // small caves that has been visited at least once
    fun exploredSmallCaves(): List<String> = explored.filter { it.isStringLowerCase() }.toList()

    fun printPath() {
        explored.forEach { print("$it,") }
        println(name)
    }

    fun printNode() {
        println("Node $name, level $level, explored $explored")
    }

    fun printTree() {
        println("Node $name, level $level, explored $explored, children:")
        children.forEach {
            (0..level).forEach { _ -> print("\t ") }
            it.printTree()
        }
    }
}