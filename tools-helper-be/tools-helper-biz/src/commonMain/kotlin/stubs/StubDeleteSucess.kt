package stubs

import ToolsHelperContext
import ToolsHelperCorSettings
import ToolsHelperStub
import models.ToolsHelperOrderId
import models.ToolsHelperOrderStatus
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker
import ru.patseev.helper.logging.LogLevel

fun ICorChainDsl<ToolsHelperContext>.stubDeleteSuccess(
    title: String, corSettings: ToolsHelperCorSettings
) = worker {
    this.title = title
    this.description = """
        Случай успешного удаления
    """.trimIndent()

    on { stubCase == ToolsHelperStubs.SUCCESS && state == ToolsHelperState.RUNNING }
    val logger = corSettings.loggerProvider.logger("StubDeleteSuccess")
    handle {
        logger.doWithLogging(id = this.requestId.asString(), LogLevel.DEBUG) {
            state = ToolsHelperState.FINISHING
            val stub = ToolsHelperStub.prepareResult {
                orderRequest.id.takeIf { it != ToolsHelperOrderId.NONE }?.also { this.id = it }
                orderRequest.orderStatus.takeIf { it != ToolsHelperOrderStatus.NONE }?.also { this.orderStatus = it }
            }
            orderResponse = stub
        }
    }
}