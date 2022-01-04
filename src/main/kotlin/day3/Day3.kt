package day3

import util.Util
import java.io.File

fun main() {
    val day3 = Day3()
    val file = File("src/main/kotlin/day3/input")
    val report = Util.log(
        "parse report",
        System.currentTimeMillis(),
        day3.parseReport(file)
    ).filterIsInstance<String>()
    Util.log(
        "calculate power consumption",
        System.currentTimeMillis(),
        day3.calculatePowerConsumption(report)
    )
    Util.log(
        "verify life support rating",
        System.currentTimeMillis(),
        day3.verifyLifeSupportRating(report)
    )
}

class Day3 {
    fun parseReport(file: File): List<String> = file.readLines()

    fun calculatePowerConsumption(report: List<String>): Int {
        val gammaRate = calculateGammaRate(report)
        val epsilonRate = calculateEpsilonRate(gammaRate)
        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun calculateGammaRate(report: List<String>): String {
        var result = ""
        for (col in report[0].indices) {                                // iterate over each bit/column, i = 0..12
            val nrOfOnes = report.count { row -> row[col] == '1' }
            val nrOfZeros = report.count { row -> row[col] == '0' }
            val mostCommonValueInCol = if (nrOfOnes >= nrOfZeros) '1' else '0'
            result += mostCommonValueInCol
        }
        return result
    }

    fun calculateEpsilonRate(gammaRate: String): String {
        var epsilonRate = ""
        for (element in gammaRate) {
            epsilonRate += if (element == '1') '0' else '1'
        }
        return epsilonRate
    }

    fun verifyLifeSupportRating(report: List<String>): Int {
        val oxygenGeneratorRating = findOxygenGeneratorRating(report)
        val co2ScrubberRating = findCO2ScrubberRating(report)
        return oxygenGeneratorRating * co2ScrubberRating
    }

    fun findOxygenGeneratorRating(report: List<String>): Int {
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

    fun findCO2ScrubberRating(report: List<String>): Int {
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