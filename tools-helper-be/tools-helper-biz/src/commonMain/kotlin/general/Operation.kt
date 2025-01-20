package general

import ToolsHelperContext
import models.ToolsHelperCommand
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.chain

fun ICorChainDsl<ToolsHelperContext>.operation(
    title: String,
    command: ToolsHelperCommand,
    block: ICorChainDsl<ToolsHelperContext>.() -> Unit
) = chain {
    block()
    this.title = title
    on { this.command == command && state == ToolsHelperState.RUNNING }
}