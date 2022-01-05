package day3

import util.Day
import java.io.File

class Day3: Day<List<String>>(3, "Binary Diagnostic") {
    override fun parse(file: File): List<String> = file.readLines()

    override fun partOne(): Int {
        val gammaRate = calculateGammaRate(data)
        val epsilonRate = calculateEpsilonRate(gammaRate)
        return gammaRate.toInt(2) * epsilonRate.toInt(2)    }

    private fun calculateGammaRate(report: List<String>): String {
        var result = ""
        for (col in report[0].indices) {                                // iterate over each bit/column, i = 0..12
            val nrOfOnes = report.count { row -> row[col] == '1' }
            val nrOfZeros = report.count { row -> row[col] == '0' }
            val mostCommonValueInCol = if (nrOfOnes >= nrOfZeros) '1' else '0'
            result += mostCommonValueInCol
        }
        return result
    }

    private fun calculateEpsilonRate(gammaRate: String): String {
        var epsilonRate = ""
        for (element in gammaRate) {
            epsilonRate += if (element == '1') '0' else '1'
        }
        return epsilonRate
    }

    override fun partTwo(): Int {
        val oxygenGeneratorRating = findOxygenGeneratorRating(data)
        val co2ScrubberRating = findCO2ScrubberRating(data)
        return oxygenGeneratorRating * co2ScrubberRating
    }

    private fun findOxygenGeneratorRating(report: List<String>): Int {
        var filteredNumbers = report
        for (col in report[0].indices) {

            val nrOfOnes = filteredNumbers.count { row -> row[col] == '1' }
            val nrOfZeros = filteredNumbers.count { row -> row[col] == '0' }
            // if equally common, set '1'
            val mostCommonValueInCol = if (nrOfOnes >= nrOfZeros) '1' else '0'

            // filter out rows who does not have leastCommonValueInCol in the current column
            filteredNumbers = filteredNumbers.filter { number -> number[col] == mostCommonValueInCol }
            if (filteredNumbers.size == 1) {
                return filteredNumbers[0].toInt(2)
            }
        }
        error("oxygen generator rating not found!")
    }

    private fun findCO2ScrubberRating(report: List<String>): Int {
        var filteredNumbers = report
        for (col in report[0].indices) {

            val nrOfOnes = filteredNumbers.count { row -> row[col] == '1' }
            val nrOfZeros = filteredNumbers.count { row -> row[col] == '0' }
            // if equally common, set '0'
            val leastCommonValueInCol = if (nrOfOnes < nrOfZeros) '1' else '0'

            // filter out rows who does not have leastCommonValueInCol in the current column
            filteredNumbers = filteredNumbers.filter { number -> number[col] == leastCommonValueInCol }
            if (filteredNumbers.size == 1) {
                return filteredNumbers[0].toInt(2)
            }
        }
        error("oxygen generator rating not found!")
    }
}