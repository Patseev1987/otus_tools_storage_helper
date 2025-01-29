package stubs

import ToolsHelperContext
import helper.fail
import models.ToolsHelperError
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.stubDbError(title: String) = worker {
    this.title = title
    this.description = """
        Случай ошибки базы данных
    """.trimIndent()
    on { stubCase == ToolsHelperStubs.DB_ERROR && state == ToolsHelperState.RUNNING}
    handle {
        fail(
            ToolsHelperError(
                group = "internal",
                code = "internal-db",
                message = "Internal error"
            )
        )
    }
}