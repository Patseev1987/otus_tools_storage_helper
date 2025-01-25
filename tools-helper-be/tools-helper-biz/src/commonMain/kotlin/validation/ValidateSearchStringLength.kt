package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import models.ToolsHelperOrderFilter
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.chain
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateSearchStringLength(title: String) = chain {
    this.title = title
    description = """
                Валидация длины строки поиска в поисковых фильтрах. Допустимые значения:
                - null - не выполняем поиск по строке
                - 4-${ToolsHelperOrderFilter.MAX_SEARCH_STRING_LENGTH} - допустимая длина
                - больше ${ToolsHelperOrderFilter.MAX_SEARCH_STRING_LENGTH} - слишком длинная строка
    """.trimIndent()

    on { state == ToolsHelperState.RUNNING }

    worker("Очистка строки поиска") { orderFilterValidating.searchString = orderFilterValidating.searchString.trim() }

    worker {
        this.title = "Проверяем минимальную длину строки"
        on { orderFilterValidating.searchString.length in 1 until 4 }
        handle {
            fail(
                errorValidation(
                    field = "searchString",
                    violationCode = "tooShort",
                    description = "Search string must contain at least 3 symbols"
                )
            )
        }
    }

    worker {
        this.title = "Проверяем длину строки не более ${ToolsHelperOrderFilter.MAX_SEARCH_STRING_LENGTH}"
        on { orderFilterValidating.searchString.length > ToolsHelperOrderFilter.MAX_SEARCH_STRING_LENGTH }
        handle {
            fail(
                errorValidation(
                    field = "searchString",
                    violationCode = "tooLong",
                    description = "Search string must be no more than ${ToolsHelperOrderFilter.MAX_SEARCH_STRING_LENGTH} symbols long"
                )
            )
        }
    }

}