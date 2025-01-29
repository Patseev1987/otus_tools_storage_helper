package stubs

import ToolsHelperContext
import ToolsHelperCorSettings
import ToolsHelperStub
import models.*
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker
import ru.patseev.helper.logging.LogLevel


fun ICorChainDsl<ToolsHelperContext>.stubUpdateSuccess(
    title: String,
    corSettings: ToolsHelperCorSettings
) = worker {
    this.title = title
    this.description = """
        Случай успешного обновления заказа
    """.trimIndent()

    on { stubCase == ToolsHelperStubs.SUCCESS && state == ToolsHelperState.RUNNING }
    val logger = corSettings.loggerProvider.logger("StubReadSuccess")
    handle {
        logger.doWithLogging(id = this.requestId.asString(), LogLevel.DEBUG) {
            state = ToolsHelperState.FINISHING
            val stub = ToolsHelperStub.prepareResult {
                orderRequest.id.takeIf { it != ToolsHelperOrderId.NONE }?.also { this.id = it }
                orderRequest.orderStatus.takeIf { it != ToolsHelperOrderStatus.NONE }?.also { this.orderStatus = it }
                orderRequest.operationId.takeIf { it != ToolsHelperOperationId.NONE }?.also { this.operationId = it }
                orderRequest.ownerId.takeIf { it != ToolsHelperEmployeeId.NONE }?.also { this.ownerId = it }
                orderRequest.partCount.takeIf { it > 0 }?.also { this.partCount = it }
            }
            orderResponse = stub
        }
    }
}