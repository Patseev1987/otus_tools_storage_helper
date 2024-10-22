package domainTemp

import kotlin.random.Random

data class Task(
    val id: Long,
    val operation: String,
    val tools: List<String>,
    val partCount: Int
) {
    companion object {
        fun getExampleTasks(): List<Task> {
            val tasks: MutableList<Task> = mutableListOf()
            for (i in 1..10) {
                tasks.add(
                    Task(
                        id = i.toLong(),
                        operation = getOperation(),
                        partCount = 100 - Random.nextInt(100),
                        tools = listOf(" Резец $i", "Штангенциркуль")
                    )
                )
            }
            return tasks
        }

        private fun getOperation(): String {
            return when (Random.nextInt(3)) {
                0 -> "Деталь 13.11.120\nоперация 35"
                1 -> "Деталь 01.55.333\n" +
                        "операция 40"

                2 -> "Деталь 66.11.350\n" +
                        "операция 75"

                else -> "Деталь 55.7.99\n" +
                        "операция 120"
            }
        }
    }
}
