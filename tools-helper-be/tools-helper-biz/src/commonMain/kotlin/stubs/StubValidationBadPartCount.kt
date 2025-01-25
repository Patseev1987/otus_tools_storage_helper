package stubs

import helper.fail
import models.ToolsHelperError
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker
import ToolsHelperContext

fun ICorChainDsl<ToolsHelperContext>.stubValidationBadPartsCount(title: String) = worker {
    this.title = title
    this.description = """
        Случай ошибки валидации количества деталей
    """.trimIndent()
    on { stubCase == ToolsHelperStubs.BAD_PARTS_COUNT && state == ToolsHelperState.RUNNING }
    handle {
        fail(
            ToolsHelperError(
                group = "validation",
                code = "validation-parts count",
                field = "part count",
                message = "Wrong parts count"
            )
        )
    }
}