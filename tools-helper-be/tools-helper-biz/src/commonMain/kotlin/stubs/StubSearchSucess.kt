package stubs

import ToolsHelperContext
import ToolsHelperCorSettings
import ToolsHelperStub
import models.ToolsHelperOrderStatus
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker
import ru.patseev.helper.logging.LogLevel

fun ICorChainDsl<ToolsHelperContext>.stubSearchSuccess(
    title: String, corSettings: ToolsHelperCorSettings
) = worker {
    this.title = title
    this.description = """
        Случай успешного поиска
    """.trimIndent()

    on { stubCase == ToolsHelperStubs.SUCCESS && state == ToolsHelperState.RUNNING }
    val logger = corSettings.loggerProvider.logger("StubSearchSuccess")
    handle {
        logger.doWithLogging(id = this.requestId.asString(), LogLevel.DEBUG) {
            state = ToolsHelperState.FINISHING
            ordersResponses.addAll(
                ToolsHelperStub.prepareSearchList(
                    ownerId = "qwe",
                    status = ToolsHelperOrderStatus.IN_PROGRESS,
                    filter = orderFilterRequest.searchString
                )
            )
        }
    }
}