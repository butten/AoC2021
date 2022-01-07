package day8

import util.Day
import java.io.File

class Day8 : Day<List<Entry>>(8, "Seven Segment Search") {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day8().runPartOne()
            Day8().runPartTwo()
        }
    }

    override fun parse(file: File): List<Entry> {
        return file.readLines()
            .map {
                it.split(" | ", " ")                // split into separate values by using delimiters
                    .map { value -> value.alphabetized() }    // take the opportunity to sort each value
            }
            .map { Entry(it.subList(0, 10), it.subList(10, 14)) }
    }

    // "cba" -> "abc"
    private fun String.alphabetized() = String(toCharArray().apply { sort() })

    // returns number of overlaps between two digits
    private fun String.overlaps(s: String): Int {
        return this.count { s.contains(it) }
    }

    // returns the overlapping segments between two digits
    private fun String.common(s: String): String {
        return this.toList().intersect(s.toList().toSet()).joinToString("")
    }

    private fun String.minus(s: String): String {
        return this.filterNot { s.contains(it) }
    }

    private fun List<String>.firstOrEmpty() = this.firstOrNull() ?: ""

    private fun List<String>.firstOrEmpty(predicate: (String) -> Boolean): String {
        for (element in this) if (predicate(element)) return element
        return ""
    }

    override fun partOne(): Any {
        return data.sumOf { entry ->
            entry.fdov.count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
        }
    }

    override fun partTwo(): Any {
        data.forEach{ entry ->

            // digit 8 is just all segments
            if (entry.segs[8].isEmpty()) {
                entry.segs[8] = "abcdefg"
            }

            // digits 1, 4, 7 each use a unique number of segments
            entry.segs[1] = entry.getSegmentsWithNrOfBits(2).firstOrEmpty()
            entry.segs[4] = entry.getSegmentsWithNrOfBits(4).firstOrEmpty()
            entry.segs[7] = entry.getSegmentsWithNrOfBits(3).firstOrEmpty()

            // find digit 2 if it exists in entry
            if (entry.segs[4].isNotEmpty() && entry.segs[2].isEmpty()) {
                // digit 2 has 2 overlaps with digit 4 (unique for 5 segment digits)
                entry.segs[2] = entry.getSegmentsWithNrOfBits(5).firstOrEmpty { it.overlaps(entry.segs[4]) == 2 }
            } else if (entry.segs[9].isNotEmpty() && entry.segs[2].isEmpty()) {
                // digit 2 has 4 overlaps with digit 9 (unique for 5 segment digits)
                entry.segs[2] = entry.getSegmentsWithNrOfBits(5).firstOrEmpty { it.overlaps(entry.segs[9]) == 4 }
            }

            // find digit 3 if it exists in entry
            if (entry.segs[1].isNotEmpty() && entry.segs[3].isEmpty()) {
                // digit 3 has 2 overlap with digit 1 (unique for 5 segment digits)
                entry.segs[3] = entry.getSegmentsWithNrOfBits(5).firstOrEmpty { it.overlaps(entry.segs[1]) == 2 }
            } else if (entry.segs[7].isNotEmpty() && entry.segs[3].isEmpty()) {
                // digit 3 has 3 overlaps with digit 7 (unique for 5 segment digits)
                entry.segs[3] = entry.getSegmentsWithNrOfBits(5).firstOrEmpty { it.overlaps(entry.segs[7]) == 3 }
            } else if (entry.segs[2].isNotEmpty() && entry.segs[3].isEmpty()) {
                // digit 3 has 4 overlaps with digit 2 (unique for 5 segments digits)
                entry.segs[3] = entry.getSegmentsWithNrOfBits(5).firstOrEmpty { it.overlaps(entry.segs[2]) == 4 }
            } else if (entry.segs[3].isEmpty()) {
                error("digit 1 or digit 7 is needed to find digit 3")
            }

            // find digit 6 if it exists in entry
            if (entry.segs[1].isNotEmpty() && entry.segs[6].isEmpty()) {
                // digit 6 has 1 overlap with digit 1 (unique for 6 segment digits)
                entry.segs[6] = entry.getSegmentsWithNrOfBits(6).firstOrEmpty { it.overlaps(entry.segs[1]) == 1 }
            } else if (entry.segs[7].isNotEmpty() && entry.segs[6].isEmpty()) {
                // digit 6 has 2 overlaps with digit 7 (unique for 6 segment digits)
                entry.segs[6] = entry.getSegmentsWithNrOfBits(6).firstOrEmpty { it.overlaps(entry.segs[7]) == 2 }
            } else if (entry.segs[6].isEmpty()) {
                error("digit 1 or digit 7 is needed to find digit 6")
            }

            // find digit 9 if it exists in entry
            if (entry.segs[4].isNotEmpty() && entry.segs[9].isEmpty()) {
                // digit 9 has 4 overlaps with digit 4 (unique for 6 segment digits)
                entry.segs[9] = entry.getSegmentsWithNrOfBits(6).firstOrEmpty { it.overlaps(entry.segs[4]) == 4 }
            } else if (entry.segs[3].isNotEmpty() && entry.segs[9].isEmpty()) {
                // digit 9 has 5 overlaps with digit 3 (unique for 6 segment digits)
                entry.segs[9] = entry.getSegmentsWithNrOfBits(6).firstOrEmpty { it.overlaps(entry.segs[3]) == 5 }
            } else if (entry.segs[9].isEmpty()) {
                error("digit 3 or digit 4 is needed to find digit 9")
            }

            // find digit 5 if it exists in entry
            if (entry.segs[4].isNotEmpty() && entry.segs[5].isEmpty()) {
                // digit 5 has 3 overlaps with digit 2 (unique for 5 segment digits)
                entry.segs[5] = entry.getSegmentsWithNrOfBits(5).firstOrEmpty { it.overlaps(entry.segs[2]) == 3 }
            }

            // find digit 0 if it exists in entry
            if (entry.segs[2].isNotEmpty() && entry.segs[0].isEmpty()) {
                // digit 0 is a 6 bit segment that is not digit 6 or 9
                entry.segs[0] = entry.getSegmentsWithNrOfBits(6)
                    .filterNot { it == entry.segs[6] || it == entry.segs[9] }.firstOrEmpty()
            }

            // if we have digits 2 and 3 -> we can figure out digit 5
            if (entry.segs[2].isNotEmpty() && entry.segs[3].isNotEmpty() && entry.segs[5].isEmpty()) {
                entry.segs[5] = entry.getSegmentsWithNrOfBits(5)
                    .filterNot { it == entry.segs[2] && it == entry.segs[3] }
                    .firstOrEmpty()
            }

            // if we have digits 2 and 5, we can figure out digit 3
            if (entry.segs[2].isNotEmpty() && entry.segs[5].isNotEmpty() && entry.segs[3].isEmpty()) {
                entry.segs[3] = entry.getSegmentsWithNrOfBits(5)
                    .filterNot { it == entry.segs[2] && it == entry.segs[5] }
                    .firstOrEmpty()
            }

            // finding overlaps between digits 4 and 7 gives digit 1
            if (entry.segs[4].isNotEmpty() && entry.segs[7].isNotEmpty() && entry.segs[1].isEmpty()) {
                entry.segs[1] = entry.segs[4].common(entry.segs[7])
            }

            // find digit 4 by figuring out the segment wiring
            if (entry.segs[4].isEmpty()) {
                val segA = entry.segs[7].minus(entry.segs[1])
                val segBEG = entry.segs[0].minus(entry.segs[7])
                val segBE = entry.segs[6].minus(entry.segs[3])
                val segG = segBEG.minus(segBE)
                entry.segs[4] = entry.segs[9].minus(segA).minus(segG)
            }

            val d0 = entry.segs.indexOf(entry.fdov[0])
            val d1 = entry.segs.indexOf(entry.fdov[1])
            val d2 = entry.segs.indexOf(entry.fdov[2])
            val d3 = entry.segs.indexOf(entry.fdov[3])
            entry.value = d0 * 1000 + d1 * 100 + d2 * 10 + d3
        }
        return data.sumOf { it.value }
    }
}

data class Entry(val usp: List<String>, val fdov: List<String>) {
    var segs = Array(10) { "" }
    var value: Int = 0

    private fun getAllValues(): List<String> {
        return listOf(usp, fdov).flatten().distinct()
    }

    fun getSegmentsWithNrOfBits(n: Int): List<String> {
        return getAllValues().filter { it.length == n }
    }
    
    override fun toString(): String {
        return "$usp | $fdov"
    }
}