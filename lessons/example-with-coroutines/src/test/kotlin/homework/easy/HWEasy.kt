package homework.easy

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.test.Test


class HWEasy {

    @Test
    fun easyHw() {
        val startTimer = System.currentTimeMillis()
        val numbers = generateNumbers()
        val toFind = 10
        val toFindOther = 1000
        runBlocking {
            val firstCase = async {
                findNumberInList(toFind, numbers)
            }

            val secondCase = async {
                findNumberInList(toFindOther, numbers)
            }
            val foundNumbers = listOf(
                firstCase.await(),
                secondCase.await()
            )

            foundNumbers.forEach {
                if (it != -1) {
                    println("Your number $it found!")
                } else {
                    println("Not found number $toFind || $toFindOther")
                }
            }
        }
        val stopTimer = System.currentTimeMillis()
        println("Took ${stopTimer - startTimer} ms")
    }
}
