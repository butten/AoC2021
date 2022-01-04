package day5

typealias Lines = List<Line>

fun Lines.filterDiagLines() = this.filter { it.x1 == it.x2 || it.y1 == it.y2 }