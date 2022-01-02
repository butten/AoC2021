package util

class Util {

    companion object {
        fun log(task: String, startTime: Long, result: Int) {
            val execTime = System.currentTimeMillis() - startTime
            println("$task: $result. Time: $execTime millis")
        }
    }
}