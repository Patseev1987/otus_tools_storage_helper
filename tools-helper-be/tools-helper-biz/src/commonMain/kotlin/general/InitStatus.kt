package general


import ToolsHelperContext
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.initStatus(title: String) = worker() {
    this.title = title
    this.description = """
        Этот обработчик устанавливает статус обработки, если он не выбран
    """.trimIndent()
    on { state == ToolsHelperState.NONE }
    handle { state = ToolsHelperState.RUNNING }
}