package stubs

import ToolsHelperContext
import helper.fail
import models.ToolsHelperError
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.stubNoCase(title: String) = worker {
    this.title = title
    this.description = """
        Случай, когда запрошенной заглушки нет
    """.trimIndent()
    on { state == ToolsHelperState.RUNNING }
    handle {
        fail(
            ToolsHelperError(
                code = "validation",
                group = "validation",
                field = "stub",
                message = "Wrong stub case is requested: ${stubCase.name}"
            )
        )
    }
}