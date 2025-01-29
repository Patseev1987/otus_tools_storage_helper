package general

import ToolsHelperContext
import models.ToolsHelperState
import models.ToolsHelperWorkMode
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.chain

fun ICorChainDsl<ToolsHelperContext>.stubs(
    title: String,
    block: ICorChainDsl<ToolsHelperContext>.() -> Unit
) = chain {
    block()
    this.title = title
    on { workMode == ToolsHelperWorkMode.STUB && state == ToolsHelperState.RUNNING }
}