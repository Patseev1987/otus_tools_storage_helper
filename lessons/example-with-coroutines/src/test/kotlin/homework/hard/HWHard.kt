package homework.hard


import homework.hard.dto.Dictionary
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.test.Test


class HWHard {
    @Test
    fun hardHw() {
        val start = System.currentTimeMillis()
        val dictionaryApi = DictionaryApi()
        val words = FileReader.readFile().split(" ", "\n").toSet()

        runBlocking {
            val dictionaries =
                findWords(dictionaryApi, words, Locale.EN)

            dictionaries.filterNotNull().map { dictionary ->
                print("For word ${dictionary.word} i found examples: ")
                println(
                    dictionary.meanings
                        .mapNotNull { definition ->
                            val r = definition.definitions
                                .mapNotNull { it.example.takeIf { it?.isNotBlank() == true } }
                                .takeIf { it.isNotEmpty() }
                            r
                        }
                        .takeIf { it.isNotEmpty() }
                )
            }
        }
        val end = System.currentTimeMillis()
        println("Took ${end - start} ms")
    }

    private fun findWords(
        dictionaryApi: DictionaryApi,
        words: Set<String>,
        @Suppress("SameParameterValue") locale: Locale
    ): List<Dictionary?> {
        var result: List<Dictionary?> = emptyList()

        runBlocking {
            result = words.map {
                async {
                    dictionaryApi.findWord(locale, it)
                }
            }.map {
                it.await()
            }
        }
        return result
    }


    object FileReader {
        fun readFile(): String =
            File(
                this::class.java.classLoader.getResource("words.txt")?.toURI()
                    ?: throw RuntimeException("Can't read file")
            ).readText()
    }
}
