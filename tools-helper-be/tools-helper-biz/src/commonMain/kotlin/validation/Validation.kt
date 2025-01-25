package validation

import ToolsHelperContext
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.chain

fun ICorChainDsl<ToolsHelperContext>.validation(block: ICorChainDsl<ToolsHelperContext>.() -> Unit) = chain {
    block()
    title = "Валидация"

    on { state == ToolsHelperState.RUNNING }
}