package stubs

import ToolsHelperContext
import ToolsHelperCorSettings
import ToolsHelperStub
import models.*
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker
import ru.patseev.helper.logging.LogLevel

fun ICorChainDsl<ToolsHelperContext>.stubCreateSuccess(
    title: String,
    corSettings: ToolsHelperCorSettings
) = worker {
    this.title = title
    this.description = """
        Случай успешного создания заказа
    """.trimIndent()

    on { stubCase == ToolsHelperStubs.SUCCESS && state == ToolsHelperState.RUNNING }
    val logger = corSettings.loggerProvider.logger("StubCreateSuccess")
    handle {
        logger.doWithLogging(id = this.requestId.asString(), LogLevel.DEBUG) {
            state = ToolsHelperState.FINISHING
            val stub = ToolsHelperStub.prepareResult {
                println("""
                    [[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[
                   partCount =  ${this.partCount}
                """.trimIndent())
                orderRequest.id.takeIf { it != ToolsHelperOrderId.NONE }?.also { this.id = it }
                orderRequest.operationId.takeIf { it != ToolsHelperOperationId.NONE }?.also { this.operationId = it }
                orderRequest.ownerId.takeIf { it != ToolsHelperEmployeeId.NONE }?.also { this.ownerId = it }
                orderRequest.partCount.takeIf { it > 0 }?.also { this.partCount = it }
                orderRequest.orderStatus.takeIf { it == ToolsHelperOrderStatus.CREATED }?.also { this.orderStatus = it }
            }
            orderResponse = stub
        }
    }
}