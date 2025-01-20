package stubs

import ToolsHelperContext
import models.ToolsHelperError
import models.ToolsHelperState
import helper.fail
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.stubValidationBadId(title: String) = worker {
    this.title = title
    this.description = """
        Случай ошибки валидации ид
    """.trimIndent()
    on { stubCase == ToolsHelperStubs.BAD_ID && state == ToolsHelperState.RUNNING }
    handle {
        fail(
            ToolsHelperError(
                group = "validation",
                code = "validation-id",
                field = "id",
                message = "Bad id"
            )
        )
    }
}