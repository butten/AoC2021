package util

class Timer {
    val startTime = System.currentTimeMillis()

    fun read(): Long {
        return System.currentTimeMillis() - startTime
    }
}