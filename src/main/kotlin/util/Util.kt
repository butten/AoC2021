package util

class Util {

    companion object {
        fun log(task: String, startTime: Long, result: Int) {
            val execTime = System.currentTimeMillis() - startTime
            println("$task: $result. Time: $execTime millis")
        }

        fun log(task: String, startTime: Long, result: Any): Any {
            val execTime = System.currentTimeMillis() - startTime
            println("$task. Time: $execTime millis")
            return result
        }

        fun log(task: String, startTime: Long, result: List<*>): List<*> {
            val execTime = System.currentTimeMillis() - startTime
            println("$task. Time: $execTime millis")
            return result
        }
    }
}